package client.cloudy.com.annotation_compiler;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

import client.cloudy.com.annotation.BindPath;

/**
 * 获取注解、处理注解（生成代码）
 *
 * 生成的代码位置：依赖注解处理器的module的build文件中 （clean-rebuild）
 * build/generated/ap_generated_sources
 */

/**
 * 注解处理器在使用前需要先向JVM注册，在module的META-INF目录下新建services目录，并创建一个名为javax.annotation.processing.Processor的文件，
 * 在此文件内逐行声明注解处理器。同样地，此处需要声明的也是处理器类的完全限定名。
 *
 * 另一个简便的方法是使用Google提供的auto-services库，在build.gradle中引入com.google.auto.service:auto-service:1.0-rc2，
 * 并在处理器类上添加注解@AutoService(Processor.class)，auto-services也是一个注解处理器，会在编译时为该module生成声明文件。
 */
@AutoService(Processor.class)
public class AnnotationCompiler extends AbstractProcessor {

    Filer filer; //创建一个生成java文件的对象

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    /**
     * 声明注解支持的java版本
     *
     * @return
     */
    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return processingEnv.getSourceVersion();
//    }
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 返回处理器想要处理的注解类型，此处需返回一个包含了所有注解完全限定名的集合。
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * //生成文件代码
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        //获取所有依赖"AnnotationCompiler"注解处理module 通过BindPath注解的类对象、类节点。这里返回一个set集合，集合中的元素继承Element（元素节点）。
        //解释：有几个业务逻辑模块依赖"AnnotationCompiler"的module，process方法就会执行几次. 则文件也会创建多个（需要考虑命名不同）
        //类节点 TypeElement
        //方法节点 ExecutableElement
        //变量节点 VariableElement
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(BindPath.class);

        Map<String, String> map = new HashMap<>();

        for (Element element : elementsAnnotatedWith) {
            //typeElement对象就相当于我们的类对象 如：targetActivity  注意是对象
            TypeElement typeElement = (TypeElement) element;
            //通过类对象获取类注解
            BindPath bindPath = typeElement.getAnnotation(BindPath.class);
            //通过注解拿到注解中的key 也就是path路径
            String path = bindPath.path();
            //获取 包名加类名
            Name activityName = typeElement.getQualifiedName();
            //建立映射关系：将path 和 类 关联起来
            map.put(path,activityName+".class");
        }

        if (map.size() == 0) {
            return false;
        }

        Writer writer = null;

        //java文件名。多个依赖多次执行多个类名，类名需不一样
        String className = "ActivityUtils" + System.currentTimeMillis();

        try {
            //生成java源文件
            // 大坑：文件名和包名必须一样
            JavaFileObject classFile = filer.createSourceFile("com.example.zhangtuo.learndeme.z_router." + className);
            //开始写
            writer = classFile.openWriter();
            writer.write("package com.example.zhangtuo.learndeme.z_router;\n" +
                    "\n" +
                    "import z_router.MyRouter;\n" +
                    "import z_router.IRouter;\n" +
                    "\n" +
                    "public class " + className + " implements IRouter {\n" +
                    "    @Override\n" +
                    "    public void putActivity() {\n");

            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String activityKey = iterator.next();
                String clsName = map.get(activityKey);
                writer.write("        MyRouter.getInstance().addActivity(");
                writer.write("\"" + activityKey + "\"," + clsName + ");\n");
            }
            writer.write("\n}\n" +
                    "}");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
