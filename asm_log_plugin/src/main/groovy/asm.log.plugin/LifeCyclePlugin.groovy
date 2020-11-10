package asm.log.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifeCyclePlugin implements Plugin<Project>{
    void apply(Project project){
        System.out.println("== LifeCycle Plugin gradle Plugin")
    }
}
