package com.hermitowo.advancedtfctech.compat.jei;

import java.util.Arrays;
import com.hermitowo.advancedtfctech.api.crafting.PowerLoomRecipe;
import com.hermitowo.advancedtfctech.common.blocks.ATTBlocks;
import com.hermitowo.advancedtfctech.common.items.ATTItems;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.dries007.tfc.compat.jei.category.BaseRecipeCategory;

import static com.hermitowo.advancedtfctech.AdvancedTFCTech.*;

public class PowerLoomRecipeCategory extends BaseRecipeCategory<PowerLoomRecipe>
{
    private static final ResourceLocation ICONS = new ResourceLocation(MOD_ID, "textures/gui/jei/jei.png");
    private final IDrawableStatic slot;
    private final IDrawableStatic arrows;
    private final IDrawableAnimated arrowsAnimated;

    public PowerLoomRecipeCategory(RecipeType<PowerLoomRecipe> type, IGuiHelper helper)
    {
        super(type, helper, helper.createBlankDrawable(120, 38), new ItemStack(ATTBlocks.Multiblocks.POWER_LOOM.get()));
        arrows = helper.createDrawable(ICONS, 0, 118, 22, 16);
        IDrawableStatic arrowAnimated = helper.createDrawable(ICONS, 22, 118, 22, 16);
        this.arrowsAnimated = helper.createAnimatedDrawable(arrowAnimated, 80, IDrawableAnimated.StartDirection.LEFT, false);
        this.slot = helper.getSlotDrawable();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PowerLoomRecipe recipe, IFocusGroup focuses)
    {
        IRecipeSlotBuilder input = builder.addSlot(RecipeIngredientRole.INPUT, 20, 0);
        IRecipeSlotBuilder pirn = builder.addSlot(RecipeIngredientRole.INPUT, 20, 20);
        IRecipeSlotBuilder output = builder.addSlot(RecipeIngredientRole.OUTPUT, 84, 0);
        IRecipeSlotBuilder secondaryOutput = builder.addSlot(RecipeIngredientRole.OUTPUT, 84, 20);

        input.addItemStacks(Arrays.asList(recipe.inputs[0].getMatchingStacks()));
        pirn.addItemStacks(Arrays.asList(recipe.inputs[1].getMatchingStacks()));
        output.addItemStack(recipe.output.get());
        secondaryOutput.addItemStack(ATTItems.PIRN.get().getDefaultInstance());

        input.setBackground(slot, -1, -1);
        pirn.setBackground(slot, -1, -1);
        output.setBackground(slot, -1, -1);
        secondaryOutput.setBackground(slot, -1, -1);
    }

    @Override
    public void draw(PowerLoomRecipe recipe, IRecipeSlotsView recipeSlots, PoseStack stack, double mouseX, double mouseY)
    {
        arrows.draw(stack, 50, 12);
        arrowsAnimated.draw(stack, 50, 12);
    }
}
