package p455w0rd.danknull.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

public class ModGlobals {

	public static final String MODID_PWLIB = "p455w0rdslib";

	public static final String MODID = "danknull";
	public static final String VERSION = "1.5.53";
	public static final String NAME = "/dank/null";
	public static final String SERVER_PROXY = "p455w0rd.danknull.proxy.CommonProxy";
	public static final String CLIENT_PROXY = "p455w0rd.danknull.proxy.ClientProxy";
	public static final String GUI_FACTORY = "p455w0rd.danknull.init.ModGuiFactory";
	public static final String CONFIG_FILE = "config/DankNull.cfg";
	public static final String DEPENDANCIES = "required-after:" + MODID_PWLIB + "@[2.2.98,);after:stg;after:jei;after:waila;after:theoneprobe;after:nei";

	public static boolean GUI_DANKNULL_ISOPEN = false;
	public static float TIME = 0.0F;

	public static class Rarities {

		private static final IRarity[] RARITY_CACHE = new IRarity[] {

				createRarity("dn:redstone", TextFormatting.RED), createRarity("dn:redstone", TextFormatting.BLUE), createRarity("dn:redstone", TextFormatting.WHITE), createRarity("dn:redstone", TextFormatting.YELLOW), createRarity("dn:redstone", TextFormatting.AQUA), createRarity("dn:redstone", TextFormatting.GREEN), createRarity("dn:redstone", TextFormatting.LIGHT_PURPLE)

		};

		public static IRarity getRarityFromMeta(final int meta) {
			return RARITY_CACHE[meta];
		}

		private static IRarity createRarity(final String name, final TextFormatting color) {
			return new IRarity() {

				@Override
				public TextFormatting getColor() {
					return color;
				}

				@Override
				public String getName() {
					return name;
				}

			};
		}

	}

	public static class Textures {

		public static final ResourceLocation DOCK_TEXTURE = new ResourceLocation(ModGlobals.MODID, "textures/models/danknull_dock.png");

	}

	public static class NBT {

		// Vanilla
		public static final String ID = "id";
		public static final String DAMAGE = "Damage";
		public static final String BLOCKENTITYTAG = "BlockEntityTag";
		public static final String SLOT = "Slot";

		// Custom
		public static final String DANKNULL_INVENTORY = "danknull-inventory";
		public static final String DOCKEDSTACK = "DankNullStack";
		public static final String MODE = "Mode";
		public static final String STACK = "Stack";
		public static final String OREDICT = "OreDict";
		public static final String SELECTEDINDEX = "selectedIndex";
		public static final String REALCOUNT = "RealCount";
		public static final String UUID = "UUID";
		public static final String EXTRACTION_MODES = "ExtractionModes";
		public static final String PLACEMENT_MODES = "PlacementModes";
		public static final String OREDICT_MODES = "OreDictModes";
		public static final String LOCKED = "Locked";

	}

	public static enum DankNullTier {

			REDSTONE, LAPIS, IRON, GOLD, DIAMOND, EMERALD, CREATIVE;

		public static DankNullTier[] VALUES = values();
		//@formatter:off
		private static final int[] OPAQUE_HEX_COLORS = new int[]{
				0xFFEC4848,
				0xFF4885EC,
				0xFFFFFFFF,
				0xFFFFFF00,
				0xFF00FFFF,
				0xFF17FF6D,
				0xFF8F15D4
		};

		private static final int[] HEX_COLORS = new int[] {
				0x99EC4848,
				0x994885EC,
				0x99FFFFFF,
				0x99FFFF00,
				0x9900FFFF,
				0x9917FF6D,
				0x998F15D4
		};
		//@formatter:on

		public ResourceLocation getDankNullRegistryName() {
			return new ResourceLocation(ModGlobals.MODID, getUnlocalizedNameForDankNull());
		}

		public ResourceLocation getDankNullPanelRegistryName() {
			return new ResourceLocation(ModGlobals.MODID, getUnlocalizedNameForPanel());
		}

		public String getUnlocalizedNameForDankNull() {
			return "dank_null_" + ordinal();
		}

		public String getUnlocalizedNameForPanel() {
			return "dank_null_panel_" + ordinal();
		}

		public int getMaxStackSize() {
			final int level = ordinal() + 1;
			if (level >= 6) {
				return Integer.MAX_VALUE;
			}
			return level * 128 * level;
		}

		public int getNumRows() {
			int numRows = ordinal();
			if (isCreative()) {
				numRows--;
			}
			return numRows + 1;
		}

		public int getNumRowsMultiplier() {
			return getNumRows() - 1;
		}

		public boolean isCreative() {
			return ordinal() == 6;
		}

		// 140=player inv
		public int getGuiHeight() {
			return 140 + getNumRowsMultiplier() * 20 + getNumRowsMultiplier() + 1;
		}

		public ResourceLocation getGuiBackground() {
			return new ResourceLocation(ModGlobals.MODID, "textures/gui/danknullscreen" + (getNumRowsMultiplier() + (isCreative() ? 1 : 0)) + ".png");
		}

		public IRarity getRarity() {
			return Rarities.getRarityFromMeta(ordinal());
		}

		public int getHexColor(final boolean opaque) {
			return opaque ? OPAQUE_HEX_COLORS[ordinal()] : HEX_COLORS[ordinal()];
		}

	}

}
