package com.piggsoft.lucky.api_config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * <br>Created by fire pigg on 2016/01/19.
 *
 * @author piggsoft@163.com
 */
public class ApiScanner extends ClassPathBeanDefinitionScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiScanner.class);

    private ApiHelper apiHelper;

    private  boolean acceptAllClass = true;

    public ApiScanner(BeanDefinitionRegistry registry, ApiHelper apiHelper) {
        super(registry);
        this.apiHelper = apiHelper;
    }

    public void registerFilters() {

        if (acceptAllClass) {
            // default include filter that accepts all classes
            addIncludeFilter(new TypeFilter() {
                @Override
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }

        // exclude package-info.java
        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            LOGGER.warn("No Bean was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        } else {
            processBeanDefinitions(beanDefinitions);
        }

        return beanDefinitions;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        for (BeanDefinitionHolder holder : beanDefinitions) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(holder.getBeanDefinition().getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
            for (Method method : methods) {
                Api api = AnnotationUtils.findAnnotation(method, Api.class);
                if (api == null) {
                    continue;
                }
                processApiDefinitions(clazz, method, api);
            }
        }
    }

    private void processApiDefinitions(Class<?> clzz, Method method, Api api) {
        apiHelper.add(api.value(), clzz, method);
    }

    public boolean isAcceptAllClass() {
        return acceptAllClass;
    }

    public void setAcceptAllClass(boolean acceptAllClass) {
        this.acceptAllClass = acceptAllClass;
    }

}
