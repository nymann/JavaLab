package dk.sdu.mmmi.cbse.common.spi;

import java.util.*;

public class SPILocator {

    @SuppressWarnings("rawtypes")
    private static final Map<Class, ServiceLoader> loaderMap = new HashMap<>();

    private SPILocator() {
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> locateAll(Class<T> service) {
        ServiceLoader<T> loader = loaderMap.get(service);

        if (loader == null) {
            loader = ServiceLoader.load(service);
            loaderMap.put(service, loader);
        }

        List<T> results = new ArrayList<T>();

        try {
            for (T instance : loader) {
                results.add(instance);
            }
        } catch (ServiceConfigurationError serviceError) {
            serviceError.printStackTrace();
        }

        System.out.println("Found " + results.size() + " implementations for interface: " + service.getName());

        return results;
    }
}
