package pl.gremoria;

import com.example.annotation.ProvideService;

@ProvideService
public class RetrofitSome {

    public Object retrofit;

    public RestApi restApi() {
        return ((RestApi) retrofit);
    }
}


