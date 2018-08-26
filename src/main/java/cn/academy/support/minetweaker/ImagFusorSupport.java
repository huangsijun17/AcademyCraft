package cn.academy.support.minetweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static cn.academy.crafting.ImagFusorRecipes.INSTANCE;
import static cn.academy.support.minetweaker.MTSupport.toStack;
/**
 * 
 * @author 3TUSK
 */
@ZenClass("mods.academycraft.ImagFusor")
public class ImagFusorSupport {

    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input, int liquidAmount) {
        CraftTweakerAPI.apply(new AddImagFusorRecipe(input, output, liquidAmount));
    }
    
    private static class AddImagFusorRecipe implements IAction
    {
        ItemStack input, output;
        int liquidAmount;
        
        public AddImagFusorRecipe(IItemStack input, IItemStack output, int liquidAmount) {
            this.input = toStack(input);
            this.output = toStack(output);
            this.liquidAmount = liquidAmount;
        }

        @Override
        public void apply() {
            INSTANCE.addRecipe(input, liquidAmount, output);
        }

        @Override
        public String describe() {
            return "Add extra ImagFusor recipe for " + input.getUnlocalizedName();
        }
        
    }
}