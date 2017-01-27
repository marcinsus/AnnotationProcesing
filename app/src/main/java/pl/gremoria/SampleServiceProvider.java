package pl.gremoria;


import com.packageName.RestServiceProvider;

final class SampleServiceProviderdsadsadsa {

    private static SampleService original;
    private static SampleService override;

    public static SampleService get() {
        if (override != null) {
            return override;
        } else {
            return originService();
        }
    }

    private static SampleService originService() {
        if (original != null) {
            return original;
        } else {
            original = initializer();
        }
        return original;
    }

    private static SampleService initializer() {
        return new SampleService(RestServiceProvider.get());
    }

    public static void setOverride(SampleService overrideService) {
        override = overrideService;
    }
}
