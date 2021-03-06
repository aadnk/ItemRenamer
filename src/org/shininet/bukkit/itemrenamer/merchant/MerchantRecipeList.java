package org.shininet.bukkit.itemrenamer.merchant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class MerchantRecipeList extends ArrayList<MerchantRecipe> {
	/**
	 * Generated by Eclipse.
	 */
	private static final long serialVersionUID = 570516632213722592L;

	public void writeRecipiesToStream(DataOutputStream output) throws IOException {
        output.writeByte((byte)(this.size() & 255));

        for (int i = 0; i < this.size(); i++) {
            MerchantRecipe recipe = (MerchantRecipe)this.get(i);
            StreamTools.writeItemStack(output, recipe.getItemToBuy());
            StreamTools.writeItemStack(output, recipe.getItemToSell());
            
            ItemStack secondItem = recipe.getSecondItemToBuy();
            output.writeBoolean(secondItem != null);

            if (secondItem != null) {
            	StreamTools.writeItemStack(output, secondItem);
            }

            output.writeBoolean(recipe.isCanUse());
        }
    }

    public static MerchantRecipeList readRecipiesFromStream(DataInputStream input) throws IOException {
    	MerchantRecipeList recipeList = new MerchantRecipeList();
        int size = input.readByte() & 255;

        for (int i = 0; i < size; i++) {
            ItemStack toBuy = StreamTools.readItemStack(input);
            ItemStack toSell = StreamTools.readItemStack(input);
            ItemStack toBuy2 = null;

            if (input.readBoolean()) {
                toBuy2 = StreamTools.readItemStack(input);
            }

            boolean canUse = input.readBoolean();
            MerchantRecipe recipe = new MerchantRecipe(toBuy, toBuy2, toSell, canUse);

            recipeList.add(recipe);
        }

        return recipeList;
    }
}
