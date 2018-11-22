package util;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class S {

    public static String ccat(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) sb.append(string);
        return sb.toString();
    }

    /**
     * Prepends the Wicked Arrow's MOD_ID to <var>string</var>.
     *
     * @param string the {@link String} to prepend to
     * @return a new {@link String} with the content "{@link WickedArrows#MOD_ID}:<var>string</var>"
     */
    public static String qualify(String string) {
        return ccat(WickedArrows.MOD_ID, ":", string);
    }

    public static IBlockState getBs(Block block) {
        return Block.getStateById(Block.getIdFromBlock(block));
    }

}
