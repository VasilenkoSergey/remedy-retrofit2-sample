package io.vasilenko.remedy.retrofit.sample.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vasilenko.remedy.retrofit.sample.service.FakeRestServiceClient;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PluginModule extends AbstractModule {

    @Provides
    @Singleton
    FakeRestServiceClient provideFakeRestServerClient(Retrofit retrofit) {
        return retrofit.create(FakeRestServiceClient.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Configuration configuration) {
        return new Retrofit.Builder()
                .baseUrl(configuration.getString("base_url"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Configuration provideConfiguration() throws ConfigurationException {
        return new PropertiesConfiguration("application.properties");
    }
}
