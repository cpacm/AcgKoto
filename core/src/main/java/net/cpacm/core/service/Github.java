package net.cpacm.core.service;

import net.cpacm.core.bean.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * <description>
 * Auther: cpacm
 * Date: 2016/2/1 0001
 */
public interface Github {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Test>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}
