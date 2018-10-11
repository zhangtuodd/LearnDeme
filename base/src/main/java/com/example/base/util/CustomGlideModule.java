package com.example.base.util;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * glide 4x 新特性
 * <p>
 * 用GlideModule注解，使GlideApp关联到Glide
 *
 * @author zhangtuo
 * @date 2018/7/2
 */

@GlideModule
public class CustomGlideModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}