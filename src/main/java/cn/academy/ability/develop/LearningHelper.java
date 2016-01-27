/**
* Copyright (c) Lambda Innovation, 2013-2016
* This file is part of the AcademyCraft mod.
* https://github.com/LambdaInnovation/AcademyCraft
* Licensed under GPLv3, see project root for more information.
*/
package cn.academy.ability.develop;

import cn.academy.ability.api.Category;
import cn.academy.ability.api.Skill;
import cn.academy.ability.api.data.AbilityData;
import cn.academy.ability.api.data.CPData;
import cn.academy.ability.develop.action.IDevelopAction;
import cn.academy.ability.develop.condition.IDevCondition;
import net.minecraft.entity.player.EntityPlayer;

/**
 * All sorts of judging utilities about ability learning.
 * Available in both client and server.
 * @author WeAthFolD
 */
public class LearningHelper {
    
    /**
     * @return Whether the given player can level up currently
     */
    public static boolean canLevelUp(DeveloperType type, AbilityData aData) {
        Category c = aData.getCategory();
        if(c == null)
            return true;
        return CPData.get(aData.getEntity()).canLevelUp();
    }
    
    /**
     * Skills that can be potentially learned will be displayed on the Skill Tree gui.
     */
    public static boolean canBePotentiallyLearned(AbilityData data, Skill skill) {
        return data.getLevel() >= skill.getLevel() &&
                (!data.isSkillLearned(skill) && 
                    (skill.getParent() == null || data.isSkillLearned(skill.getParent())));
    }
    
    /**
     * @return Whether the given skill can be learned.
     */
    public static boolean canLearn(AbilityData data, IDeveloper dev, Skill skill) {
        for(IDevCondition cond : skill.getDevConditions()) {
            if(!cond.accepts(data, dev, skill))
                return false;
        }
        return true;
    }

    public static double getEstimatedConsumption(EntityPlayer player, DeveloperType blktype, IDevelopAction type) {
        return blktype.getCPS() * type.getStimulations(player);
    }
    
}