package org.example.enums;


import org.example.enums.base.BaseStringEnum;

/**
 * @ClassName TemplateMsgEnum
 * @Description TODO
 * @date 2022/8/24 20:01
 * @Version 1.0
 * @Author liukai
 */
public enum TemplateMsgEnum implements BaseStringEnum {

    TEST_MSG("PyWAaDk9oriqZIrO0wUIRGp0iIHROGijY4fM5os9wHU"),

    WEATHER_MSG("a-emEBWsNAr4RvCkKmoP91P6yEjvRZ1joAbCb8--l5U"),

    ONE_DAY_GOOD_MOOD("GMEIs5M94S12kAkkPCSia5nKQcejr7u1gaKM10zsVbw");

    private final String value;

    TemplateMsgEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
