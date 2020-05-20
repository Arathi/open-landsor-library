package com.undsf.pcr.oll.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Arathi on 2020-05-20.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "accuracy",
        "atk",
        "craft_flg",
        "def",
        "description",
        "display_item",
        "dodge",
        "enable_donation",
        "energy_recovery_rate",
        "energy_reduce_rate",
        "equipment_enhance_point",
        "equipment_id",
        "equipment_name",
        "hp",
        "hp_recovery_rate",
        "item_type",
        "life_steal",
        "magic_critical",
        "magic_def",
        "magic_penetrate",
        "magic_str",
        "physical_critical",
        "physical_penetrate",
        "promotion_level",
        "require_level",
        "sale_price",
        "wave_energy_recovery",
        "wave_hp_recovery"
})
@Slf4j
public class Equipment {
    /**
     * 命中
     */
    @JsonProperty("accuracy")
    public String accuracy;

    /**
     * 攻击
     */
    @JsonProperty("atk")
    public String atk;

    /**
     * 可制造
     */
    @JsonProperty("craft_flg")
    public String craftFlg;

    /**
     * 防御
     */
    @JsonProperty("def")
    public String def;

    /**
     * 描述
     */
    @JsonProperty("description")
    public String description;

    /**
     * 是否显示
     */
    @JsonProperty("display_item")
    public String displayItem;

    /**
     * 闪避
     */
    @JsonProperty("dodge")
    public String dodge;

    /**
     * 是否允许赠送
     */
    @JsonProperty("enable_donation")
    public String enableDonation;

    /**
     * TP恢复率
     */
    @JsonProperty("energy_recovery_rate")
    public String energyRecoveryRate;

    /**
     * TP消耗减轻率
     */
    @JsonProperty("energy_reduce_rate")
    public String energyReduceRate;

    /**
     * 装备强化点数
     */
    @JsonProperty("equipment_enhance_point")
    public String equipmentEnhancePoint;

    /**
     * ID
     */
    @JsonProperty("equipment_id")
    public String equipmentId;

    /**
     * 名称
     */
    @JsonProperty("equipment_name")
    public String equipmentName;

    /**
     * HP
     */
    @JsonProperty("hp")
    public String hp;

    /**
     * HP回复率
     */
    @JsonProperty("hp_recovery_rate")
    public String hpRecoveryRate;

    /**
     * 类型
     */
    @JsonProperty("item_type")
    public String itemType;

    /**
     * 生命吸收
     */
    @JsonProperty("life_steal")
    public String lifeSteal;

    /**
     * 魔法暴击
     */
    @JsonProperty("magic_critical")
    public String magicCritical;

    /**
     * 魔法防御
     */
    @JsonProperty("magic_def")
    public String magicDef;

    /**
     * 魔法穿刺（0）
     */
    @JsonProperty("magic_penetrate")
    public String magicPenetrate;

    /**
     * 魔法攻击
     */
    @JsonProperty("magic_str")
    public String magicStr;

    /**
     * 物理暴击
     */
    @JsonProperty("physical_critical")
    public String physicalCritical;

    /**
     * 物理穿刺（0）
     */
    @JsonProperty("physical_penetrate")
    public String physicalPenetrate;

    /**
     * 稀有度（？）
     */
    @JsonProperty("promotion_level")
    public String promotionLevel;

    /**
     * 可装备等级
     */
    @JsonProperty("require_level")
    public String requireLevel;

    /**
     * 卖出价格
     */
    @JsonProperty("sale_price")
    public String salePrice;

    /**
     * 每波结束TP恢复
     */
    @JsonProperty("wave_energy_recovery")
    public String waveEnergyRecovery;

    /**
     * 每波结束HP恢复
     */
    @JsonProperty("wave_hp_recovery")
    public String waveHpRecovery;

    public com.undsf.pcr.oll.entities.Equipment toEntity() {
        try {
            int id = Integer.parseInt(equipmentId);
            String name = equipmentName;
            int type = Integer.parseInt(itemType);

            com.undsf.pcr.oll.entities.Equipment equip = new com.undsf.pcr.oll.entities.Equipment(id, name, type);

            equip.description = description;
            equip.craft = craftFlg.equals("1");
            equip.enhancePoint = (int) Float.parseFloat(equipmentEnhancePoint);
            equip.salePrice = (int) Float.parseFloat(salePrice);
            equip.rareRank = (int) Float.parseFloat(promotionLevel);
            equip.level = (int) Float.parseFloat(requireLevel);

            equip.hp = (int) Float.parseFloat(hp);
            equip.hpRecoveryRate = (int) Float.parseFloat(hpRecoveryRate);
            equip.tpRecoveryRate = (int) Float.parseFloat(energyRecoveryRate);
            equip.tpReduceRate = (int) Float.parseFloat(energyReduceRate);
            equip.accuracy = (int) Float.parseFloat(accuracy);
            equip.evasion = (int) Float.parseFloat(dodge);
            equip.lifesteal = (int) Float.parseFloat(lifeSteal);
            equip.waveHpRecovery = (int) Float.parseFloat(waveHpRecovery);
            equip.waveTpRecovery = (int) Float.parseFloat(waveEnergyRecovery);

            equip.atk = (int) Float.parseFloat(atk);
            equip.def = (int) Float.parseFloat(def);
            equip.critical = (int) Float.parseFloat(physicalCritical);

            equip.magicAtk = (int) Float.parseFloat(magicStr);
            equip.magicDef = (int) Float.parseFloat(magicDef);
            equip.magicCritical = (int) Float.parseFloat(magicCritical);

            return equip;
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}