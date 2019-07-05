package io.vasilenko.remedy.retrofit.sample;

import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.Value;
import com.bmc.arsys.pluginsvr.plugins.ARFilterAPIPlugin;
import com.bmc.arsys.pluginsvr.plugins.ARPluginContext;
import com.bmc.thirdparty.org.slf4j.Logger;
import com.bmc.thirdparty.org.slf4j.LoggerFactory;
import com.google.inject.Guice;
import com.google.inject.Inject;
import io.vasilenko.remedy.retrofit.sample.di.PluginModule;
import io.vasilenko.remedy.retrofit.sample.dto.User;
import io.vasilenko.remedy.retrofit.sample.exception.PluginException;
import io.vasilenko.remedy.retrofit.sample.service.FakeRestServiceClient;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrofitSamplePlugin extends ARFilterAPIPlugin {

    @Inject
    private FakeRestServiceClient restServiceClient;

    private final Logger log = LoggerFactory.getLogger(RetrofitSamplePlugin.class);

    @Override
    public void initialize(ARPluginContext context) {
        Guice.createInjector(new PluginModule()).injectMembers(this);
        log.info("initialized");
    }

    @Override
    public List<Value> filterAPICall(ARPluginContext context, List<Value> inputValues) throws ARException {
        Call<List<User>> call = restServiceClient.getUsers();
        List<User> users;
        try {
            Response<List<User>> response = call.execute();
            users = response.body();
        } catch (IOException e) {
            String message = e.getMessage();
            log.error(message, e);
            throw new PluginException(message);
        }
        log.info("users: {}", users);
        return new ArrayList<>();
    }
}
