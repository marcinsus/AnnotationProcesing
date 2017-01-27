package pl.gremoria;

import com.example.annotation.ProvideService;

@ProvideService
public class SampleService {
    public final RetrofitSome retrofitSome;

    public SampleService(RetrofitSome retrofitSome) {
        this.retrofitSome = retrofitSome;
    }

    public void sendRequest() {
        retrofitSome.restApi().someFun();
    }
}
