package yazdaniscodelab.retrofit_restapi_clickevent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yazdani on 5/24/2017.
 */

public class RestManager {

    private FlowerServices mFlowerServices;

    public FlowerServices getmFlowerServices(){

        if (mFlowerServices==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mFlowerServices=retrofit.create(FlowerServices.class);
        }

        return mFlowerServices;
    }

}
