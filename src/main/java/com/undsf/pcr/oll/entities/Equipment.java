package com.undsf.pcr.oll.entities;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Arathi on 2020-05-20.
 */
@Entity
@Table(name = "equipments")
public class Equipment {
    // region 物品基本信息
    /**
     * ID
     */
    @Id
    public Integer id;

    /**
     * 名称（简中）
     */
    @Column
    public String name;

    /**
     * 名称（正中）
     */
    @Column(name = "name_zhtw")
    public String nameZhTw;

    /**
     * 名称（日语）
     */
    @Column(name = "name_jajp")
    public String nameJaJp;

    /**
     * 描述（简中）
     */
    @Column
    public String description;

    /**
     * 描述（正中）
     */
    @Column(name = "description_zhtw")
    public String descriptionZhTw;

    /**
     * 描述（日语）
     */
    @Column(name = "description_jajp")
    public String descriptionJaJp;

    /**
     * 需要合成
     */
    @Column
    public Boolean craft;

    /**
     * 强化点数
     */
    @Column(name = "enhance_point")
    public Integer enhancePoint;

    /**
     * 售出价格
     */
    @Column(name = "sale_price")
    public Integer salePrice;

    /**
     * 稀有度
     */
    @Column(name = "rare_rank")
    public Integer rareRank;

    /**
     * 可装备等级
     */
    @Column
    public Integer level;

    /**
     * 图标（B站wiki用）
     */
    @Column(name = "icon_url")
    public String iconUrl;
    // endregion

    // region 角色基本属性
    /**
     * HP
     */
    @Column
    public Integer hp;

    /**
     * HP恢复率
     */
    @Column(name = "hp_recovery_rate")
    public Integer hpRecoveryRate;

    /**
     * TP恢复率
     */
    @Column(name = "tp_recovery_rate")
    public Integer tpRecoveryRate;

    /**
     * TP消耗减轻率
     */
    @Column(name = "tp_reduce_rate")
    public Integer tpReduceRate;

    /**
     * 命中
     */
    @Column
    public Integer accuracy;

    /**
     * 闪避
     */
    @Column
    public Integer evasion;

    /**
     * 生命吸收
     */
    @Column
    public Integer lifesteal;

    /**
     * 每波结束HP恢复
     */
    @Column(name = "wave_hp_recovery")
    public Integer waveHpRecovery;

    /**
     * 每波结束TP恢复
     */
    @Column(name = "wave_tp_recovery")
    public Integer waveTpRecovery;
    // endregion

    // region 物理属性
    /**
     * 攻击力
     */
    @Column
    public Integer atk;

    /**
     * 防御力
     */
    @Column
    public Integer def;

    /**
     * 暴击
     */
    @Column
    public Integer critical;
    // endregion

    // region 魔法属性
    /**
     * 魔法攻击
     */
    @Column(name = "magic_atk")
    public Integer magicAtk;

    /**
     * 魔法防御
     */
    @Column(name = "magic_def")
    public Integer magicDef;

    /**
     * 魔法暴击
     */
    @Column(name = "magic_critical")
    public Integer magicCritical;
    // endregion

    public Equipment() {}

    public Equipment(int id, String name) {
        this.id = id;
        this.nameZhTw = name;
        // this.name = ZhConverterUtil.toSimple(name);
    }

    /**
     * 物品分类
     *
     * 10 装备
     * 11 碎片
     * 12 设计图
     * 14 公主之心？
     * @return
     */
    public int getCategory() {
        int category = id / 10000;
        return category;
    }

    /**
     * 稀有度
     *
     * 1 白装
     * 2 铜装
     * 3 银装
     * 4 金装
     * 5 紫装
     * @return
     */
    public int getRare() {
        int rare = (id / 1000) % 10;
        return rare;
    }

    /**
     * 装备类型
     *
     * 待整理
     * @return
     */
    public int getType() {
        int type = (id / 10) % 100;
        return type;
    }
}
