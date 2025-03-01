package net.hearthsim.hsmodel

import net.hearthsim.hsmodel.enum.CardId
import net.hearthsim.hsmodel.enum.HSSet
import net.hearthsim.hsmodel.enum.Type

data class Card(
    val id: String,
    val dbfId: Int,
    val type: String,
    val playerClass: String,
    val set: String,
    val name: String,
    val cost: Int? = null, // null for hero cards & enchantments
    val text: String? = null, //  null for hero cards, also for some regular cards like snowflipper penguin
    val rarity: String? = null,
    val race: String? = null,
    val attack: Int? = null,
    val health: Int? = null,
    val durability: Int? = null,
    val multiClassGroup: String? = null,
    val collectible: Boolean = false,
    val mechanics: Set<String> = emptySet()
) {
    override fun toString(): String {
        return "$name($id)"
    }

    fun isStandard(): Boolean {
        return STANDARD_SETS.contains(set)
            && !HALL_OF_FAME_CARDS.contains(id)
    }

    // https://us.battle.net/forums/en/hearthstone/topic/20758787441
    fun isDraftable(): Boolean {
        return ARENA_SETS.contains(set)
            && type != Type.HERO // DK heroes are not available
            && !(UNDRAFTABLE_CARDS.contains(id))
    }


    companion object {

        val UNKNOWN_COST = null
        const val UNKNOWN_TYPE = "UNKNOWN_TYPE"

        const val CLASS_INDEX_NEUTRAL = 10

        val ARENA_SETS = setOf(
            HSSet.CORE, // Basic
            HSSet.EXPERT1, // Classic
            HSSet.GANGS,
            HSSet.SCHOLOMANCE,
            HSSet.DARKMOON_FAIRE,
            HSSet.DALARAN, // Rise of the Shadows
            HSSet.ULDUM, // Saviors of Uldum
            HSSet.DRAGONS, // Descent of Dragons
            HSSet.YEAR_OF_THE_DRAGON, // Galakrond's awakening
            HSSet.BLACK_TEMPLE,
            HSSet.DEMON_HUNTER_INITIATE,
            HSSet.THE_BARRENS
        )

        val STANDARD_SETS = setOf(
            HSSet.CORE, // Classic
            HSSet.EXPERT1, // Basic
            HSSet.BLACK_TEMPLE,
            HSSet.DEMON_HUNTER_INITIATE,
            HSSet.SCHOLOMANCE,
            HSSet.DARKMOON_FAIRE,
            HSSet.THE_BARRENS,
            HSSet.STORMWIND
        )
        val HALL_OF_FAME_CARDS = setOf(
            CardId.ICE_BLOCK,
            CardId.MOLTEN_GIANT,
            CardId.COLDLIGHT_ORACLE,
            CardId.AZURE_DRAKE,
            CardId.SYLVANAS_WINDRUNNER,
            CardId.RAGNAROS_THE_FIRELORD,
            CardId.POWER_OVERWHELMING,
            CardId.ICE_LANCE,
            CardId.CONCEAL,
            CardId.DOOMGUARD,
            CardId.NATURALIZE,
            CardId.DIVINE_FAVOR,
            CardId.BAKU_THE_MOONEATER,
            CardId.GENN_GREYMANE,
            CardId.GLOOM_STAG,
            CardId.BLACK_CAT,
            CardId.GLITTER_MOTH,
            CardId.MURKSPARK_EEL,
            CardId.ACOLYTE_OF_PAIN,
            CardId.SPELLBREAKER,
            CardId.MIND_CONTROL_TECH,
            CardId.MOUNTAIN_GIANT,
            CardId.LEEROY_JENKINS,
            CardId.AUCHENAI_SOULPRIEST,
            CardId.HOLY_FIRE,
            CardId.SHADOWFORM,
            CardId.PROPHET_VELEN,
            CardId.DIVINE_SPIRIT,
            CardId.NORTHSHIRE_CLERIC
        )

        val UNDRAFTABLE_CARDS = setOf(CardId.VICIOUS_FLEDGLING,
            // quests
            CardId.JUNGLE_GIANTS,
            CardId.THE_MARSH_QUEEN,
            CardId.OPEN_THE_WAYGATE,
            CardId.THE_LAST_KALEIDOSAUR,
            CardId.AWAKEN_THE_MAKERS,
            CardId.THE_CAVERNS_BELOW,
            CardId.UNITE_THE_MURLOCS,
            CardId.LAKKARI_SACRIFICE,
            CardId.FIRE_PLUMES_HEART,
            // cthun
            CardId.KLAXXI_AMBERWEAVER,
            CardId.DARK_ARAKKOA,
            CardId.CULT_SORCERER,
            CardId.HOODED_ACOLYTE,
            CardId.TWILIGHT_DARKMENDER,
            CardId.BLADE_OF_CTHUN,
            CardId.USHER_OF_SOULS,
            CardId.ANCIENT_SHIELDBEARER,
            CardId.TWILIGHT_GEOMANCER,
            CardId.DISCIPLE_OF_CTHUN,
            CardId.TWILIGHT_ELDER,
            CardId.CTHUNS_CHOSEN,
            CardId.CRAZED_WORSHIPPER,
            CardId.SKERAM_CULTIST,
            CardId.TWIN_EMPEROR_VEKLOR,
            CardId.DOOMCALLER,
            CardId.CTHUN,
            // other
            CardId.DUST_DEVIL,
            CardId.TOTEMIC_MIGHT,
            CardId.ANCESTRAL_HEALING,
            CardId.WINDSPEAKER,
            CardId.SACRIFICIAL_PACT,
            CardId.SENSE_DEMONS,
            CardId.FACELESS_SUMMONER,
            CardId.FELSTALKER,
            CardId.SAVAGERY,
            CardId.SOUL_OF_THE_FOREST,
            CardId.MARK_OF_NATURE,
            CardId.WARSONG_COMMANDER,
            CardId.RAMPAGE,
            CardId.STARVING_BUZZARD,
            CardId.TIMBER_WOLF,
            CardId.SNIPE,
            CardId.MIND_BLAST,
            CardId.LIGHTWELL,
            CardId.PURIFY,
            CardId.INNER_FIRE
        )
    }
}
