package test;

public enum AnimationConst
{
    police("police", "man", 170.0, 150.0, 93.0, "weaponlist", ""),
    assault01("assault01", "man", 170.0, 150.0, 93.0, "weaponlist", ""),
    pix_police("pix_police", "man", 170.0, 150.0, 93.0, "weaponlist", ""),
    police_2("police_2", "man", 170.0, 150.0, 93.0, "weaponlist", ""),
    turkey("turkey", "turkey", 170.0, 150.0, 93.0, "weaponlist_null", ""),
    vippet("vippet", "vippet", 170.0, 150.0, 93.0, "weaponlist_null", ""),;
    public String model_name;
    public String anim_name;
    public double view_height;
    public double normal_view_height;
    public double duck_view_height;
    public String weaponlist;
    /**
     * 程序笔记
     */
    public String desc;

    AnimationConst(String model_name, String anim_name, double view_height, double normal_view_height, double duck_view_height, String weaponlist, String desc)
    {
        this.model_name = model_name;
        this.anim_name = anim_name;
        this.view_height = view_height;
        this.normal_view_height = normal_view_height;
        this.duck_view_height = duck_view_height;
        this.weaponlist = weaponlist;
        this.desc = desc;
    }

    public String getModel_name()
    {
        return model_name;
    }

    public String getAnim_name()
    {
        return anim_name;
    }

    public double getView_height()
    {
        return view_height;
    }

    public double getNormal_view_height()
    {
        return normal_view_height;
    }

    public double getDuck_view_height()
    {
        return duck_view_height;
    }

    public String getWeaponlist()
    {
        return weaponlist;
    }

    public String getDesc()
    {
        return desc;
    }
}
