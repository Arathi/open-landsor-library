package com.undsf.pcr.oll.entities;

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
    public int id;

    /**
     * 名称
     */
    @Column
    public String name;

    /**
     * 类型
     */
    @Column
    public int type;

    /**
     * 描述
     */
    @Column
    public String description;

    /**
     * 需要合成
     */
    @Column
    public boolean craft;

    /**
     * 强化点数
     */
    @Column(name = "enhance_point")
    public int enhancePoint;

    /**
     * 售出价格
     */
    @Column(name = "sale_price")
    public int salePrice;

    /**
     * 稀有度
     */
    @Column(name = "rare_rank")
    public int rareRank;

    /**
     * 可装备等级
     */
    @Column
    public int level;
    // endregion

    // region 角色基本属性
    /**
     * HP
     */
    @Column
    public int hp;

    /**
     * HP恢复率
     */
    @Column(name = "hp_recovery_rate")
    public int hpRecoveryRate;

    /**
     * TP恢复率
     */
    @Column(name = "tp_recovery_rate")
    public int tpRecoveryRate;

    /**
     * TP消耗减轻率
     */
    @Column(name = "tp_reduce_rate")
    public int tpReduceRate;

    /**
     * 命中
     */
    @Column
    public int accuracy;

    /**
     * 闪避
     */
    @Column
    public int evasion;

    /**
     * 生命吸收
     */
    @Column
    public int lifesteal;

    /**
     * 每波结束HP恢复
     */
    @Column(name = "wave_hp_recovery")
    public int waveHpRecovery;

    /**
     * 每波结束TP恢复
     */
    @Column(name = "wave_tp_recovery")
    public int waveTpRecovery;
    // endregion

    // region 物理属性
    /**
     * 攻击力
     */
    @Column
    public int atk;

    /**
     * 防御力
     */
    @Column
    public int def;

    /**
     * 暴击
     */
    @Column
    public int critical;
    // endregion

    // region 魔法属性
    /**
     * 魔法攻击
     */
    @Column(name = "magic_atk")
    public int magicAtk;

    /**
     * 魔法防御
     */
    @Column(name = "magic_def")
    public int magicDef;

    /**
     * 魔法暴击
     */
    @Column(name = "magic_critical")
    public int magicCritical;
    // endregion

    public Equipment() {}

    public Equipment(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
