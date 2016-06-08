package com.proj.android.androidproj;

/**
 * Created by ronen_000 on 01/06/2016.
 */
public class ListItem {

    public int id;
    public String Name;
    public String Phone;
    public String Area;
    public String img_src;
    public String img_name;
    public String Desc;

    ListItem(int id,String Name,String Phone,String Area,String img_src,String img_name,String Desc)
    {
            this.id = id;
            this.Name = Name;
            this.Phone = Phone;
            this.Area = Area;
            this.img_src = img_src;
            this.img_name = img_name;
            this.Desc = Desc;

    }
}
