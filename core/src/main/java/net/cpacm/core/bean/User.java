package net.cpacm.core.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import net.cpacm.core.BR;

/**
 * <description>
 *
 * @Auther: cpacm
 * @Date: 2016/1/14 0014-下午 3:38
 */
public class User extends BaseObservable {
    private String name;
    private String age;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
