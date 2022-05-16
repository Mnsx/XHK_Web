package top.mnsx.xhk.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public final class SpringUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        if (getBeanFactory() == null) {
            return null;
        } else {
            T result = (T) getBeanFactory().getBean(name);
            return result;
        }
    }

    public static <T> T getBean(Class<T> name) throws BeansException {
        if (getBeanFactory() == null) {
            return null;
        } else {
            T result = (T) getBeanFactory().getBean(name);
            return result;
        }
    }

    public static boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return getBeanFactory().isSingleton(name);
    }

    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return getBeanFactory().getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return getBeanFactory().getAliases(name);
    }

}
