package com.sg.sgrr

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sg.sgrr.Retrofit.BsAPI
import com.sg.sgrr.Retrofit.characterStats
import com.sg.sgrr.Retrofit.stats
import com.sg.sgrr.fragment.total_summary_fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.sg.sgrr.Adapter.resultRecordAdapter
import com.sg.sgrr.Retrofit.games
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecordActivitys: AppCompatActivity() {

    val stats = ArrayList<stats>()
    val charImageArray = ArrayList<Int>()
    val charProfileArray = ArrayList<Int>()
    val charNameArray = ArrayList<String>()
    val top3CharArray = Array(3) {Array<characterStats>(3){ i -> characterStats(0, 0, 0, 0, 0, 0, 0) } }
    val item = mapOf(
            101101 to R.drawable.weapon_scissors,
            101102 to R.drawable.weapon_fountain_pen,
            101104 to R.drawable.weapon_kitchen_knife,
            101201 to R.drawable.weapon_army_knife,
            101301 to R.drawable.weapon_rose_knife,
            101401 to R.drawable.weapon_carnwennan,
            101402 to R.drawable.weapon_mount_slicer,
            101404 to R.drawable.weapon_vibroblade,
            101405 to R.drawable.weapon_fragarach,
            102101 to R.drawable.weapon_rusty_sword,
            102201 to R.drawable.weapon_shamshir,
            102301 to R.drawable.weapon_katana,
            102401 to R.drawable.weapon_masamune,
            102402 to R.drawable.weapon_muramasa,
            102403 to R.drawable.weapon_bastard_sword,
            102404 to R.drawable.weapon_jewel_sword,
            102405 to R.drawable.weapon_thuan_thien,
            102406 to R.drawable.weapon_arondight,
            102407 to R.drawable.weapon_excalibur,
            102408 to R.drawable.weapon_plasma_sword,
            102409 to R.drawable.weapon_laevateinn,
            102410 to R.drawable.weapon_monohoshizao,
            102411 to R.drawable.weapon_hovud,
            102501 to R.drawable.weapon_dainsleif,
            103201 to R.drawable.weapon_twin_sword,
            103301 to R.drawable.weapon_florentine,
            103401 to R.drawable.weapon_divine_dual_swords,
            103402 to R.drawable.weapon_starsteel_twin_swords,
            103501 to R.drawable.weapon_dioscuri,
            103502 to R.drawable.weapon_lloigor_and_zahr,
            104101 to R.drawable.weapon_hammer,
            104201 to R.drawable.weapon_warhammer,
            104301 to R.drawable.weapon_morning_star,
            104302 to R.drawable.weapon_black_stag_hammer,
            104401 to R.drawable.weapon_fang_mace,
            104402 to R.drawable.weapon_hammer_of_dagda,
            104403 to R.drawable.weapon_hammer_of_thor,
            104404 to R.drawable.weapon_evening_star,
            104405 to R.drawable.weapon_magicstic,
            105102 to R.drawable.weapon_pickaxe,
            105103 to R.drawable.weapon_hatchet,
            105201 to R.drawable.weapon_chain_scythe,
            105202 to R.drawable.weapon_battle_axe,
            105301 to R.drawable.weapon_light_hatchet,
            105302 to R.drawable.weapon_reaper_s_scythe,
            105401 to R.drawable.weapon_gigantic_axe,
            105402 to R.drawable.weapon_beam_axe,
            105403 to R.drawable.weapon_santa_muerte,
            105404 to R.drawable.weapon_scythe,
            105405 to R.drawable.weapon_parashu,
            105406 to R.drawable.weapon_harpe,
            107101 to R.drawable.weapon_short_spear,
            107201 to R.drawable.weapon_bamboo_spear,
            107301 to R.drawable.weapon_bident,
            107302 to R.drawable.weapon_pike,
            107303 to R.drawable.weapon_halberd_axe,
            107401 to R.drawable.weapon_sharpened_spear,
            107402 to R.drawable.weapon_gentian_silver_gun,
            107403 to R.drawable.weapon_eighteen_foot_spear,
            107404 to R.drawable.weapon_cosmic_bident,
            107405 to R.drawable.weapon_lance_of_poseidon,
            107406 to R.drawable.weapon_fangtian_huaji,
            107407 to R.drawable.weapon_blazing_lance,
            107408 to R.drawable.weapon_dragon_guandao,
            107501 to R.drawable.weapon_spear_of_longinus,
            108101 to R.drawable.weapon_branch,
            108102 to R.drawable.weapon_short_rod,
            108202 to R.drawable.weapon_long_rod,
            108301 to R.drawable.weapon_goblin_bat,
            108401 to R.drawable.weapon_umbrella,
            108402 to R.drawable.weapon_torch,
            108403 to R.drawable.weapon_statue_of_soteria,
            108404 to R.drawable.weapon_mallet,
            108501 to R.drawable.weapon_spy_umbrella,
            108502 to R.drawable.weapon_monkey_king_bar,
            109101 to R.drawable.weapon_whip,
            109201 to R.drawable.weapon_rope_cuffs,
            109202 to R.drawable.weapon_bullwhip,
            109301 to R.drawable.weapon_wind_whip,
            109401 to R.drawable.weapon_thunder_whip,
            109402 to R.drawable.weapon_lightning_whip,
            109403 to R.drawable.weapon_gleipnir,
            109404 to R.drawable.weapon_plasma_whip,
            109501 to R.drawable.weapon_whip_of_nine_bloody_tails,
            110101 to R.drawable.weapon_knuckle,
            110102 to R.drawable.weapon_cotton_work_glove,
            110201 to R.drawable.weapon_leather_glove,
            110202 to R.drawable.weapon_iron_knuckle,
            110301 to R.drawable.weapon_gauntlet,
            110302 to R.drawable.weapon_wing_knuckle,
            110401 to R.drawable.weapon_bone_gauntlet,
            110402 to R.drawable.weapon_shatter_shell_gauntlet,
            110403 to R.drawable.weapon_glass_knuckle,
            110404 to R.drawable.weapon_phoenix_gloves,
            110405 to R.drawable.weapon_one_inch_punch,
            110406 to R.drawable.weapon_divine_fist,
            110407 to R.drawable.weapon_bloodwing_knuckle,
            110408 to R.drawable.weapon_frost_petal_hand,
            110409 to R.drawable.weapon_buddha_s_palm,
            110410 to R.drawable.weapon_brasil_gauntlet,
            110411 to R.drawable.weapon_white_claw_punch,
            110412 to R.drawable.weapon_imperial_silk_glove,
            108103 to R.drawable.weapon_bamboo,
            111201 to R.drawable.weapon_tonfa,
            111301 to R.drawable.weapon_police_baton,
            111401 to R.drawable.weapon_ryukyu_tonfa,
            111402 to R.drawable.weapon_tactical_tonfa,
            111403 to R.drawable.weapon_mai_sok,
            111404 to R.drawable.weapon_plasma_tonfa,
            112103 to R.drawable.weapon_iron_ball,
            112105 to R.drawable.weapon_base_ball,
            112202 to R.drawable.weapon_grenade,
            112203 to R.drawable.weapon_molotov_cocktail,
            112204 to R.drawable.weapon_sling,
            112205 to R.drawable.weapon_signed_ball,
            112301 to R.drawable.weapon_flour_bomb,
            112302 to R.drawable.weapon_incendiary_bomb,
            112303 to R.drawable.weapon_ball_lightning,
            112304 to R.drawable.weapon_flubber,
            112305 to R.drawable.weapon_grenade_of_antioch,
            112401 to R.drawable.weapon_david_s_sling,
            112402 to R.drawable.weapon_smoke_bomb,
            112403 to R.drawable.weapon_spiky_bouncy_ball,
            112404 to R.drawable.weapon_high_explosive_grenade,
            112501 to R.drawable.weapon_ruthenium_marble,
            113101 to R.drawable.weapon_razor,
            113102 to R.drawable.weapon_playing_cards,
            113104 to R.drawable.weapon_chalk,
            113201 to R.drawable.weapon_dart,
            113202 to R.drawable.weapon_charm,
            113203 to R.drawable.weapon_vintage_card,
            113205 to R.drawable.weapon_throwing_stars,
            113206 to R.drawable.weapon_onyx_dagger,
            113207 to R.drawable.weapon_willow_leaf_spike,
            113301 to R.drawable.weapon_chakram,
            113302 to R.drawable.weapon_apricot_flower_tag,
            113401 to R.drawable.weapon_cards_of_tyranny,
            113402 to R.drawable.weapon_venom_dart,
            113403 to R.drawable.weapon_dharma_chakram,
            113404 to R.drawable.weapon_plumbata,
            113405 to R.drawable.weapon_mystic_jade_charm,
            113406 to R.drawable.weapon_fuhma_shuriken,
            113408 to R.drawable.weapon_frost_venom_dart,
            113409 to R.drawable.weapon_azure_dagger,
            113410 to R.drawable.weapon_flechette,
            113411 to R.drawable.weapon_wind_and_fire_wheels,
            113412 to R.drawable.weapon_death_rune,
            113501 to R.drawable.weapon_sudarsana,
            113502 to R.drawable.weapon_petal_torrent,
            114101 to R.drawable.weapon_bow,
            114201 to R.drawable.weapon_wooden_bow,
            114202 to R.drawable.weapon_longbow,
            114203 to R.drawable.weapon_composite_bow,
            114301 to R.drawable.weapon_strong_bow,
            114302 to R.drawable.weapon_stallion_bow,
            114303 to R.drawable.weapon_mighty_bow,
            114304 to R.drawable.weapon_pellet_bow,
            114401 to R.drawable.weapon_ancient_bolt,
            114402 to R.drawable.weapon_scorchbow,
            114403 to R.drawable.weapon_golden_ratio_bow,
            114405 to R.drawable.weapon_twinbow,
            114501 to R.drawable.weapon_elemental_bow,
            114502 to R.drawable.weapon_failnaught,
            115101 to R.drawable.weapon_short_crossbow,
            115201 to R.drawable.weapon_long_crossbow,
            115202 to R.drawable.weapon_crossbow,
            115301 to R.drawable.weapon_power_crossbow,
            115302 to R.drawable.weapon_sniper_bow,
            115303 to R.drawable.weapon_heavy_crossbow,
            115401 to R.drawable.weapon_steel_bow,
            115402 to R.drawable.weapon_the_legend_of_the_general,
            115403 to R.drawable.weapon_ballista,
            115404 to R.drawable.weapon_sniper_crossbow,
            115405 to R.drawable.weapon_the_golden_ghost,
            115501 to R.drawable.weapon_sharanga,
            116101 to R.drawable.weapon_walter_ppk,
            116201 to R.drawable.weapon_magnum_python,
            116202 to R.drawable.weapon_beretta_m92f,
            116301 to R.drawable.weapon_fn57,
            116401 to R.drawable.weapon_double_revolver_sp,
            116402 to R.drawable.weapon_magnum_anaconda,
            116403 to R.drawable.weapon_devil_s_marksman,
            116404 to R.drawable.weapon_elegance,
            116405 to R.drawable.weapon_electron_blaster,
            116406 to R.drawable.weapon_magnum_boa,
            116501 to R.drawable.weapon_kelte,
            117101 to R.drawable.weapon_fedorova,
            117201 to R.drawable.weapon_stg44,
            117301 to R.drawable.weapon_ak_47,
            117401 to R.drawable.weapon_m16a1,
            117402 to R.drawable.weapon_machine_gun,
            117403 to R.drawable.weapon_gatling_gun,
            117404 to R.drawable.weapon_ak_12,
            117405 to R.drawable.weapon_xcr,
            118101 to R.drawable.weapon_long_rifle,
            118201 to R.drawable.weapon_springfield,
            118301 to R.drawable.weapon_harpoon_gun,
            118401 to R.drawable.weapon_golden_rifle,
            118402 to R.drawable.weapon_railgun,
            118403 to R.drawable.weapon_tac_50,
            118404 to R.drawable.weapon_intervention,
            118405 to R.drawable.weapon_ntw_20,
            118406 to R.drawable.weapon_polaris,
            118501 to R.drawable.weapon_the_deadly_ray,
            119101 to R.drawable.weapon_steel_chain,
            119201 to R.drawable.weapon_nunchaku,
            119301 to R.drawable.weapon_sharper,
            119302 to R.drawable.weapon_bleeder,
            119401 to R.drawable.weapon_the_smiting_dragon,
            119402 to R.drawable.weapon_vibro_nunchaku,
            120101 to R.drawable.weapon_needle,
            120201 to R.drawable.weapon_rapier,
            120301 to R.drawable.weapon_apricot_sword,
            120302 to R.drawable.weapon_sword_of_justice,
            120401 to R.drawable.weapon_durendal_mk2,
            120402 to R.drawable.weapon_mistilteinn,
            120403 to R.drawable.weapon_volticletto,
            120404 to R.drawable.weapon_meteor_claymore,
            120405 to R.drawable.weapon_joyeuse,
            121101 to R.drawable.weapon_starter_guitar,
            121201 to R.drawable.weapon_golden_bridge,
            121202 to R.drawable.weapon_single_pick_up,
            121301 to R.drawable.weapon_ruby_special,
            121302 to R.drawable.weapon_humbucker_pickup,
            121303 to R.drawable.weapon_king_v,
            121304 to R.drawable.weapon_nocaster,
            121305 to R.drawable.weapon_super_strat,
            121306 to R.drawable.weapon_wild_horse,
            121401 to R.drawable.weapon_bohemian,
            121402 to R.drawable.weapon_stairway_to_heaven,
            121403 to R.drawable.weapon_purple_haze,
            121404 to R.drawable.weapon_satisfaction,
            121405 to R.drawable.weapon_wonderful_tonight,
            121406 to R.drawable.weapon_the_wall,
            121407 to R.drawable.weapon_teen_spirit,
            201101 to R.drawable.armor_hairband,
            201102 to R.drawable.armor_hat,
            201104 to R.drawable.armor_bike_helmet,
            201201 to R.drawable.armor_mask,
            201202 to R.drawable.armor_circlet,
            201203 to R.drawable.armor_beret,
            201204 to R.drawable.armor_chain_coif,
            201205 to R.drawable.armor_safety_helmet,
            201301 to R.drawable.armor_ballistic_helmet,
            201302 to R.drawable.armor_fire_helmet,
            201303 to R.drawable.armor_tiara,
            201401 to R.drawable.armor_crown,
            201402 to R.drawable.armor_close_helm,
            201403 to R.drawable.armor_mithril_helm,
            201404 to R.drawable.armor_crystal_tiara,
            201405 to R.drawable.armor_motorcycle_helmet,
            201406 to R.drawable.armor_tactical_ops_helmet,
            201407 to R.drawable.armor_helm_of_banneret,
            201408 to R.drawable.armor_laurel_wreath,
            201409 to R.drawable.armor_imperial_crown,
            201410 to R.drawable.armor_imperial_burgonet,
            201411 to R.drawable.armor_chinese_opera_mask,
            202101 to R.drawable.armor_windbreaker,
            202103 to R.drawable.armor_monk_s_robe,
            202105 to R.drawable.armor_wet_suit,
            202106 to R.drawable.armor_fabric_armor,
            202201 to R.drawable.armor_leather_armor,
            202202 to R.drawable.armor_leather_jacker,
            202203 to R.drawable.armor_turtle_dobok,
            202205 to R.drawable.armor_military_suit,
            202206 to R.drawable.armor_patched_robe,
            202207 to R.drawable.armor_dress,
            202209 to R.drawable.armor_bikini,
            202210 to R.drawable.armor_diving_suit,
            202301 to R.drawable.armor_rider_jacket,
            202302 to R.drawable.armor_chain_armor,
            202303 to R.drawable.armor_suit,
            202304 to R.drawable.armor_qipao,
            202305 to R.drawable.armor_sheet_metal_armor,
            202306 to R.drawable.armor_hanbok,
            202401 to R.drawable.armor_bulletproof_vest,
            202402 to R.drawable.armor_sunset_armor,
            202404 to R.drawable.armor_covert_agent_uniform,
            202405 to R.drawable.armor_optical_camouflage_suit,
            202406 to R.drawable.armor_rocker_s_jacket,
            202407 to R.drawable.armor_mithril_armor,
            202408 to R.drawable.armor_crusader_armor,
            202410 to R.drawable.armor_amazoness_armor,
            202411 to R.drawable.armor_dragon_dobok,
            202412 to R.drawable.armor_commander_s_armor,
            202413 to R.drawable.armor_butler_s_suit,
            202415 to R.drawable.armor_battle_suit,
            202416 to R.drawable.armor_blazing_dress,
            202417 to R.drawable.armor_eod_suit,
            202501 to R.drawable.armor_kabana,
            202502 to R.drawable.armor_queen_of_hearts,
            203101 to R.drawable.armor_watch,
            203102 to R.drawable.armor_bandage,
            203104 to R.drawable.armor_bracelet,
            203201 to R.drawable.armor_leather_shield,
            203202 to R.drawable.armor_squad_leader_armband,
            203203 to R.drawable.armor_bracer,
            203204 to R.drawable.armor_broken_watch,
            203301 to R.drawable.armor_sheath,
            203302 to R.drawable.armor_golden_bracelet,
            203303 to R.drawable.armor_bazuband,
            203304 to R.drawable.armor_crimson_bracelet,
            203401 to R.drawable.armor_steel_shield,
            203402 to R.drawable.armor_sword_stopper,
            203403 to R.drawable.armor_draupnir,
            203404 to R.drawable.armor_mithril_shield,
            203405 to R.drawable.armor_vital_sign_sensor,
            203406 to R.drawable.armor_creed_of_the_knight,
            203407 to R.drawable.armor_sheath_of_shahjahan,
            203408 to R.drawable.armor_cube_watch,
            203409 to R.drawable.armor_aegis,
            203410 to R.drawable.armor_tindalos_band,
            203501 to R.drawable.armor_bracelet_of_skadi,
            203502 to R.drawable.armor_radar,
            203503 to R.drawable.armor_auto_arms,
            204101 to R.drawable.armor_slippers,
            204102 to R.drawable.armor_running_shoes,
            204103 to R.drawable.armor_tights,
            204201 to R.drawable.armor_knee_pads,
            204202 to R.drawable.armor_chain_leggings,
            204203 to R.drawable.armor_high_heels,
            204204 to R.drawable.armor_heelys,
            204301 to R.drawable.armor_repaired_slippers,
            204302 to R.drawable.armor_boots,
            204401 to R.drawable.armor_steel_knee_pads,
            204402 to R.drawable.armor_feather_boots,
            204403 to R.drawable.armor_maverick_runner,
            204404 to R.drawable.armor_combat_boots,
            204405 to R.drawable.armor_killer_heels,
            204406 to R.drawable.armor_straitjacket_sneakers,
            204407 to R.drawable.armor_mithril_boots,
            204408 to R.drawable.armor_bucephalus,
            204409 to R.drawable.armor_eod_boots,
            204411 to R.drawable.armor_white_rhinos,
            204410 to R.drawable.armor_glacial_shoes,
            204501 to R.drawable.armor_boots_of_hermes,
            204502 to R.drawable.armor_red_shoes,
            205101 to R.drawable.armor_feather,
            205102 to R.drawable.armor_flower,
            205103 to R.drawable.armor_ribbon,
            205105 to R.drawable.armor_fan,
            205106 to R.drawable.armor_buddhist_scripture,
            205107 to R.drawable.armor_box,
            205108 to R.drawable.armor_holy_grail,
            205109 to R.drawable.armor_cross,
            205110 to R.drawable.armor_binoculars,
            205202 to R.drawable.armor_saint_s_relic,
            205203 to R.drawable.armor_flower_of_fate,
            205204 to R.drawable.armor_glass_pieces,
            205205 to R.drawable.armor_doll,
            205206 to R.drawable.armor_sniping_scope,
            205207 to R.drawable.armor_buddha_sarira,
            205208 to R.drawable.armor_quiver,
            205209 to R.drawable.armor_feather_duster,
            205210 to R.drawable.armor_gilded_quill_fan,
            205211 to R.drawable.armor_shaman_s_bronze,
            205301 to R.drawable.armor_powder_of_life,
            205302 to R.drawable.armor_uchiwa,
            205303 to R.drawable.armor_magazine,
            205201 to R.drawable.armor_white_crane_fan,
            205401 to R.drawable.armor_moonlight_pendant,
            205304 to R.drawable.armor_laced_quiver,
            205305 to R.drawable.armor_revenge_of_goujian,
            205404 to R.drawable.armor_schrodinger_s_box,
            205405 to R.drawable.armor_veritas_lux_mea,
            205402 to R.drawable.armor_glacial_ice,
            205403 to R.drawable.armor_true_samadhi_fire,
            205501 to R.drawable.armor_emerald_tablet
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.record_result_activity)

        // 캐릭터 이미지(Profile) Array
        charProfileArray.add(R.drawable.char_1profile)
        charProfileArray.add(R.drawable.char_2profile)
        charProfileArray.add(R.drawable.char_3profile)
        charProfileArray.add(R.drawable.char_4profile)
        charProfileArray.add(R.drawable.char_5profile)
        charProfileArray.add(R.drawable.char_6profile)
        charProfileArray.add(R.drawable.char_7profile)
        charProfileArray.add(R.drawable.char_8profile)
        charProfileArray.add(R.drawable.char_9profile)
        charProfileArray.add(R.drawable.char_10profile)
        charProfileArray.add(R.drawable.char_11profile)
        charProfileArray.add(R.drawable.char_12profile)
        charProfileArray.add(R.drawable.char_13profile)
        charProfileArray.add(R.drawable.char_14profile)
        charProfileArray.add(R.drawable.char_15profile)
        charProfileArray.add(R.drawable.char_16profile)
        charProfileArray.add(R.drawable.char_17profile)
        charProfileArray.add(R.drawable.char_18profile)
        charProfileArray.add(R.drawable.char_19profile)
        //charProfileArray.add(R.drawable.char_20profile)
        //charProfileArray.add(R.drawable.char_21profile)

        // 캐릭터 이미지(Portrait) Array
        charImageArray.add(R.drawable.char_1portrait)
        charImageArray.add(R.drawable.char_2portrait)
        charImageArray.add(R.drawable.char_3portrait)
        charImageArray.add(R.drawable.char_4portrait)
        charImageArray.add(R.drawable.char_5portrait)
        charImageArray.add(R.drawable.char_6portrait)
        charImageArray.add(R.drawable.char_7portrait)
        charImageArray.add(R.drawable.char_8portrait)
        charImageArray.add(R.drawable.char_9portrait)
        charImageArray.add(R.drawable.char_10portrait)
        charImageArray.add(R.drawable.char_11portrait)
        charImageArray.add(R.drawable.char_12portrait)
        charImageArray.add(R.drawable.char_13portrait)
        charImageArray.add(R.drawable.char_14portrait)
        charImageArray.add(R.drawable.char_15portrait)
        charImageArray.add(R.drawable.char_16portrait)
        charImageArray.add(R.drawable.char_17portrait)
        charImageArray.add(R.drawable.char_18portrait)
        charImageArray.add(R.drawable.char_19portrait)
        charImageArray.add(R.drawable.char_19portrait)
        charImageArray.add(R.drawable.char_19portrait)

        // 캐릭터 이름 Array
        charNameArray.add("재키")
        charNameArray.add("아야")
        charNameArray.add("피오라")
        charNameArray.add("매그너스")
        charNameArray.add("자히르")
        charNameArray.add("나딘")
        charNameArray.add("현우")
        charNameArray.add("하트")
        charNameArray.add("아이솔")
        charNameArray.add("리 다이린")
        charNameArray.add("유키")
        charNameArray.add("혜진")
        charNameArray.add("쇼우")
        charNameArray.add("키아라")
        charNameArray.add("시셀라")
        charNameArray.add("실비아")
        charNameArray.add("아드리아나")
        charNameArray.add("쇼이치")
        charNameArray.add("엠마")
        charNameArray.add("레녹스")
        charNameArray.add("로지")
        charNameArray.add("루크")

        val Base_URL_BSURL = "https://testbsserver.herokuapp.com"
        val retrofit = Retrofit.Builder().baseUrl(Base_URL_BSURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val client = retrofit.create(BsAPI::class.java)
        val userNumber = intent.getIntExtra("UserNumber", 0).toString()
        findViewById<TextView>(R.id.profile_name).text = intent.getStringExtra("UserNickname")
        if (userNumber != "0") {
            client.getUserStats(userNumber, "1").enqueue(object : Callback<stats> {
                override fun onResponse(call: Call<stats>, response: Response<stats>) {
                    // 비주얼 UI 조작
                    stats.add(response?.body() as stats)
                    uiSomething()
                }

                override fun onFailure(call: Call<stats>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        val recyclerview = findViewById<RecyclerView>(R.id.score)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if (userNumber != null) {
            client.getUserGames(userNumber).enqueue(object : Callback<games> {
                override fun onResponse(call: Call<games>, response: Response<games>) {
                    if (response?.body()?.userGames != null) {
                        recyclerview.adapter = resultRecordAdapter(
                                response.body()!!.userGames,
                                charImageArray,
                                item,
                                charNameArray
                        )
                    }
                }

                override fun onFailure(call: Call<games>, t: Throwable) {
                }

            })
        }

        setPieChart()
    }

    // UI 변경 함수 (여기에서 인터페이스 수정)
    fun uiSomething(){
        // 1. 캐릭터 별 데이터를 담은 2차원 배열 = charData
        val charData = calc_mostChar()

        // 2. 랭크 계산
        calc_Rank()

        // 3.
        setTotalBtn()
        supportFragmentManager.beginTransaction().replace(R.id.total_summary, total_summary_fragment()).commit()
    }

    // most 캐릭터 계산
    private fun calc_mostChar(): Array<Array<characterStats>> {
        val totalCharacter = Array(3) { Array<characterStats>(20) { i -> characterStats(i, 0, 0, 0, 0, 0, 0) } }
        
        var mostChar = 0
        var mostTotalGames = 0
        // 솔로
        for( x in stats[0].userStats[0].characterStats){
            totalCharacter[0][x.characterCode].characterCode = x.characterCode
            totalCharacter[0][x.characterCode].totalGames = x.totalGames
            totalCharacter[0][x.characterCode].maxKillings = x.maxKillings
            totalCharacter[0][x.characterCode].top3 = x.top3
            totalCharacter[0][x.characterCode].top3Rate = x.top3Rate
            totalCharacter[0][x.characterCode].averageRank = x.averageRank
            totalCharacter[0][x.characterCode].wins = x.wins
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
            // 솔로 모스트3 계산
            if( top3CharArray[0][2].totalGames < x.totalGames){
                if(top3CharArray[0][1].totalGames < x.totalGames){
                    // 모스트 1인 경우
                    if(top3CharArray[0][0].totalGames < x.totalGames){
                        top3CharArray[0][0].characterCode = x.characterCode
                        top3CharArray[0][0].totalGames = x.totalGames
                        top3CharArray[0][0].maxKillings = x.maxKillings
                        top3CharArray[0][0].top3 = x.top3
                        top3CharArray[0][0].top3Rate = x.top3Rate
                        top3CharArray[0][0].averageRank = x.averageRank
                        top3CharArray[0][0].wins = x.wins
                    }
                    // 모스트 2인 경우
                    else{
                        top3CharArray[0][1].characterCode = x.characterCode
                        top3CharArray[0][1].totalGames = x.totalGames
                        top3CharArray[0][1].maxKillings = x.maxKillings
                        top3CharArray[0][1].top3 = x.top3
                        top3CharArray[0][1].top3Rate = x.top3Rate
                        top3CharArray[0][1].averageRank = x.averageRank
                        top3CharArray[0][1].wins = x.wins
                    }
                }
                // 모스트 3인 경우
                else{
                    top3CharArray[0][2].characterCode = x.characterCode
                    top3CharArray[0][2].totalGames = x.totalGames
                    top3CharArray[0][2].maxKillings = x.maxKillings
                    top3CharArray[0][2].top3 = x.top3
                    top3CharArray[0][2].top3Rate = x.top3Rate
                    top3CharArray[0][2].averageRank = x.averageRank
                    top3CharArray[0][2].wins = x.wins
                }
            }
        }
        // 듀오
        for( x in stats[0].userStats[1].characterStats){
            totalCharacter[1][x.characterCode].characterCode = x.characterCode
            totalCharacter[1][x.characterCode].totalGames = x.totalGames
            totalCharacter[1][x.characterCode].maxKillings = x.maxKillings
            totalCharacter[1][x.characterCode].top3 = x.top3
            totalCharacter[1][x.characterCode].top3Rate = x.top3Rate
            totalCharacter[1][x.characterCode].averageRank = x.averageRank
            totalCharacter[1][x.characterCode].wins = x.wins
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
            // 듀오 모스트3 계산
            if( top3CharArray[1][2].totalGames < x.totalGames){
                if(top3CharArray[1][1].totalGames < x.totalGames){
                    // 모스트 1인 경우
                    if(top3CharArray[1][0].totalGames < x.totalGames){
                        top3CharArray[1][0].characterCode = x.characterCode
                        top3CharArray[1][0].totalGames = x.totalGames
                        top3CharArray[1][0].maxKillings = x.maxKillings
                        top3CharArray[1][0].top3 = x.top3
                        top3CharArray[1][0].top3Rate = x.top3Rate
                        top3CharArray[1][0].averageRank = x.averageRank
                        top3CharArray[1][0].wins = x.wins
                    }
                    // 모스트 2인 경우
                    else{
                        top3CharArray[1][1].characterCode = x.characterCode
                        top3CharArray[1][1].totalGames = x.totalGames
                        top3CharArray[1][1].maxKillings = x.maxKillings
                        top3CharArray[1][1].top3 = x.top3
                        top3CharArray[1][1].top3Rate = x.top3Rate
                        top3CharArray[1][1].averageRank = x.averageRank
                        top3CharArray[1][1].wins = x.wins
                    }
                }
                // 모스트 3인 경우
                else{
                    top3CharArray[1][2].characterCode = x.characterCode
                    top3CharArray[1][2].totalGames = x.totalGames
                    top3CharArray[1][2].maxKillings = x.maxKillings
                    top3CharArray[1][2].top3 = x.top3
                    top3CharArray[1][2].top3Rate = x.top3Rate
                    top3CharArray[1][2].averageRank = x.averageRank
                    top3CharArray[1][2].wins = x.wins
                }
            }
        }
        // 스쿼드
        for( x in stats[0].userStats[2].characterStats){
            totalCharacter[2][x.characterCode].characterCode = x.characterCode
            totalCharacter[2][x.characterCode].totalGames = x.totalGames
            totalCharacter[2][x.characterCode].maxKillings = x.maxKillings
            totalCharacter[2][x.characterCode].top3 = x.top3
            totalCharacter[2][x.characterCode].top3Rate = x.top3Rate
            totalCharacter[2][x.characterCode].averageRank = x.averageRank
            totalCharacter[2][x.characterCode].wins = x.wins
            if( mostTotalGames < x.totalGames ){
                mostTotalGames = x.totalGames
                mostChar = x.characterCode
            }
            // 스쿼드 모스트3 계산
            if( top3CharArray[2][2].totalGames < x.totalGames){
                if(top3CharArray[2][1].totalGames < x.totalGames){
                    // 모스트 1인 경우
                    if(top3CharArray[2][0].totalGames < x.totalGames){
                        top3CharArray[2][0].characterCode = x.characterCode
                        top3CharArray[2][0].totalGames = x.totalGames
                        top3CharArray[2][0].maxKillings = x.maxKillings
                        top3CharArray[2][0].top3 = x.top3
                        top3CharArray[2][0].top3Rate = x.top3Rate
                        top3CharArray[2][0].averageRank = x.averageRank
                        top3CharArray[2][0].wins = x.wins
                    }
                    // 모스트 2인 경우
                    else{
                        top3CharArray[2][1].characterCode = x.characterCode
                        top3CharArray[2][1].totalGames = x.totalGames
                        top3CharArray[2][1].maxKillings = x.maxKillings
                        top3CharArray[2][1].top3 = x.top3
                        top3CharArray[2][1].top3Rate = x.top3Rate
                        top3CharArray[2][1].averageRank = x.averageRank
                        top3CharArray[2][1].wins = x.wins
                    }
                }
                // 모스트 3인 경우
                else{
                    top3CharArray[2][2].characterCode = x.characterCode
                    top3CharArray[2][2].totalGames = x.totalGames
                    top3CharArray[2][2].maxKillings = x.maxKillings
                    top3CharArray[2][2].top3 = x.top3
                    top3CharArray[2][2].top3Rate = x.top3Rate
                    top3CharArray[2][2].averageRank = x.averageRank
                    top3CharArray[2][2].wins = x.wins
                }
            }
        }
        
        // 프로필 캐릭터 사진 변경
        findViewById<ImageView>(R.id.profile_image).setImageResource(charProfileArray[mostChar-1])
        return totalCharacter
    }

    // Rank, 평균 등수, 티어표(Summary) 3가지
    private fun calc_Rank() {
        val soloMMR = stats[0].userStats[0].mmr
        val soloRank = stats[0].userStats[0].rank
        val soloRankSize = stats[0].userStats[0].rankSize

        val duoMMR = stats[0].userStats[1].mmr
        val duoRank = stats[0].userStats[1].rank
        val duoRankSize = stats[0].userStats[1].rankSize

        val squadMMR = stats[0].userStats[2].mmr
        val squadRank = stats[0].userStats[2].rank
        val squadRankSize = stats[0].userStats[2].rankSize

        var maxMmr = if(soloMMR > duoMMR){
            if(soloMMR > squadMMR)
                soloMMR
            else
                squadMMR
        }else{
            if(duoMMR > squadMMR)
                duoMMR
            else
                squadMMR
        }
        var maxRankSize : Double
        var maxRank = if(soloRank > duoRank){
            if(soloRank > squadRank){
                maxRankSize = soloRankSize.toDouble()
                soloRank.toDouble()
            }else{
                maxRankSize = squadRankSize.toDouble()
                squadRank.toDouble()
            }
        }else{
            if(duoRank > squadRank){
                maxRankSize = duoRankSize.toDouble()
                duoRank.toDouble()
            }else{
                maxRankSize = squadRankSize.toDouble()
                squadRank.toDouble()
            }
        }

        /*if( soloMMR > duoMMR ){
            if( soloMMR > squadMMR){
                maxMmr = soloMMR
                maxRank = soloRank.toDouble()
                maxRankSize = soloRankSize.toDouble()
            } else {
                maxMmr = squadMMR
                maxRank = squadRank.toDouble()
                maxRankSize = squadRankSize.toDouble()
            }
        } else {
            if(duoMMR > squadMMR) {
                maxMmr = duoMMR
                maxRank = duoRank.toDouble()
                maxRankSize = duoRankSize.toDouble()
            } else {
                maxMmr = squadMMR
                maxRank = squadRank.toDouble()
                maxRankSize = squadRankSize.toDouble()
            }
        }*/

        // 프로필 랭킹
        if(maxRank == 0.0){
            findViewById<TextView>(R.id.profile_rank).text = "최고 MMR의 배치 정보가 없습니다."
        } else{
            findViewById<TextView>(R.id.profile_rank).text = "상위 ${maxRank}위 (상위 ${Math.round(maxRank / maxRankSize * 100)*10/10.0}%)"
        }

        // 평균 등수
        val soloGrade = stats[0].userStats[0].averageRank
        val duoGrade = stats[0].userStats[1].averageRank
        val squadGrade = stats[0].userStats[2].averageRank
        var averGrade = soloGrade
        var count = 1
        if(duoGrade != 0.0){
            averGrade += duoGrade
            count++
        }
        if(squadGrade != 0.0){
            averGrade += squadGrade
            count++
        }
        findViewById<TextView>(R.id.profile_averRank).text = String.format("#%.1f", averGrade / count)

        /*findViewById<ImageView>(R.id.profile_text).text = when (maxMmr) {
            in 0..399 -> {
                when(maxMmr){
                    in 0..99 -> "아이언 IV"
                    in 100..199 -> "아이언 III"
                    in 200..299 -> "아이언 II"
                    else ->  "아이언 I"

                }
            }
            in 400..799 -> {
                when(maxMmr){
                    in 400..499 -> "브론즈 IV"
                    in 500..599 -> "브론즈 III"
                    in 600..699 -> "브론즈 II"
                    else ->  "브론즈 I"
                }
            }
            in 800..1299 -> {
                when(maxMmr){
                    in 800..999 -> "실버 IV"
                    in 900..999 -> "실버 III"
                    in 1000..1199 -> "실버 II"
                    else ->  "실버 I"
                }
            }
            in 1300..1699 -> {
                when(maxMmr){
                    in 1300..1399 -> "골드 IV"
                    in 1400..1499 -> "골드 III"
                    in 1500..1599 -> "골드 II"
                    else ->  "골드 I"
                }
            }
            in 1700..2099 -> {
                when(maxMmr){
                    in 1700..1799 -> "플래티넘 IV"
                    in 1800..1899 -> "플래티넘 III"
                    in 1900..1999 -> "플래티넘 II"
                    else ->  "플래티넘 I"
                }
            }
            in 2100..2499 -> {
                when(maxMmr){
                    in 2100..2199 -> "다이아 IV"
                    in 2200..2299 -> "다이아 III"
                    in 2300..2399 -> "다이아 II"
                    else ->  "다이아 I"
                }
            }
            in 2500..2899 -> {
                when(maxMmr){
                    in 2500..2599 -> "데미갓 IV"
                    in 2600..2699 -> "데미갓 III"
                    in 2700..2799 -> "데미갓 II"
                    else ->  "데미갓 I"
                }
            }
            else -> {
                when(maxMmr){
                    in 2900..2999 -> "이터니티 IV"
                    in 3000..3099 -> "이터니티 III"
                    in 3100..3199 -> "이터니티 II"
                    else ->  "이터니티 I"
                }
            }
        }*/

        // Summary - 솔로 MMR 및 Tier 그림
        findViewById<TextView>(R.id.summary_txt_soloMMR).setText("${soloMMR} LP")
        when (soloMMR) {
            in 0..399 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_iron)
            }
            in 400..799 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_bronze)
            }
            in 800..1299 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_silver)
            }
            in 1300..1699 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_gold)
            }
            in 1700..2099 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_platinum)
            }
            in 2100..2499 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_diamond)
            }
            in 2500..2899 -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_demigod)
            }
            else -> {
                findViewById<ImageView>(R.id.summary_soloTier).setImageResource(R.drawable.tier_eternity)
            }
        }

        findViewById<TextView>(R.id.summary_txt_duoMMR).setText("${duoMMR} LP")
        when (duoMMR) {
            in 0..399 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_iron)
            }
            in 400..799 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_bronze)
            }
            in 800..1299 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_silver)
            }
            in 1300..1699 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_gold)
            }
            in 1700..2099 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_platinum)
            }
            in 2100..2499 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_diamond)
            }
            in 2500..2899 -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_demigod)
            }
            else -> {
                findViewById<ImageView>(R.id.summary_duoTier).setImageResource(R.drawable.tier_eternity)
            }
        }

        findViewById<TextView>(R.id.summary_txt_squadMMR).setText("${squadMMR} LP")
        when (squadMMR) {
            in 0..399 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_iron)
            }
            in 400..799 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_bronze)
            }
            in 800..1299 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_silver)
            }
            in 1300..1699 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_gold)
            }
            in 1700..2099 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_platinum)
            }
            in 2100..2499 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_diamond)
            }
            in 2500..2899 -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_demigod)
            }
            else -> {
                findViewById<ImageView>(R.id.summary_squadTier).setImageResource(R.drawable.tier_eternity)
            }
        }


    }

    private fun setPieChart(){
        val pieChart: PieChart = findViewById(R.id.pieChart)
        pieChart.setUsePercentValues(true)
        // 요약 설명 유무
        pieChart.description.isEnabled = false;
        // 차트 돌리면 돌아가는 속도
        pieChart.dragDecelerationFrictionCoef = 1f;

        // 그래프 가운데 Hole 유무
        pieChart.isDrawHoleEnabled = true;
        pieChart.setHoleColor(Color.WHITE);
        // 선명도 조정값
        pieChart.transparentCircleRadius = 61f;

        pieChart.setExtraOffsets(-5f,0f,-5f,-10f);

        val yValues = ArrayList<PieEntry>()
        yValues.add(PieEntry(34f, "승"))
        yValues.add(PieEntry(66f, "패"))

        val dataSet = PieDataSet(yValues, "")
        // 데이터 사이 공간 너비 값
        dataSet.sliceSpace = 1f
        dataSet.selectionShift = 0f
        dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        val data = PieData(dataSet)
        data.setValueTextSize(10f)
        data.setValueTextColor(Color.WHITE)

        pieChart.data = data
    }

    private fun setTotalBtn() {
        val totalBtnSolo = findViewById<TextView>(R.id.total_btn_solo)
        val totalBtnDuo = findViewById<TextView>(R.id.total_btn_duo)
        val totalBtnSquad = findViewById<TextView>(R.id.total_btn_squad)
        val centerSoloUnderBar = findViewById<ImageView>(R.id.center_solo_underbar)
        val centerDuoUnderBar = findViewById<ImageView>(R.id.center_duo_underbar)
        val centerSquadUnderBar = findViewById<ImageView>(R.id.center_squad_underbar)

        totalBtnSolo.setTextColor(Color.parseColor("#E8B32C"))
        centerDuoUnderBar.visibility = View.GONE
        centerSquadUnderBar.visibility = View.GONE

        // total - solo 버튼 리스너
        totalBtnSolo.setOnClickListener {

            val solo_charcode1 = top3CharArray[0][0].characterCode
            val solo_charcode2 = top3CharArray[0][1].characterCode
            val solo_charcode3 = top3CharArray[0][2].characterCode
            val solo_newFrag = supportFragmentManager.findFragmentById(R.id.total_summary) as total_summary_fragment

            if(solo_charcode1 != 0)
                solo_newFrag?.changeC1(
                        charImageArray[solo_charcode1-1],
                        charNameArray[solo_charcode1-1],
                        String.format("AVG %d %s", top3CharArray[0][0].averageRank, when(top3CharArray[0][0].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[0][0].totalGames)
                )
            if(solo_charcode2 != 0)
                solo_newFrag?.changeC2(
                        charImageArray[solo_charcode2-1],
                        charNameArray[solo_charcode2-1],
                        String.format("AVG %d %s", top3CharArray[0][1].averageRank, when(top3CharArray[0][1].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[0][1].totalGames)
                )
            if(solo_charcode3 != 0)
                solo_newFrag?.changeC3(
                        charImageArray[solo_charcode3-1],
                        charNameArray[solo_charcode3-1],
                        String.format("AVG %d %s", top3CharArray[0][2].averageRank, when(top3CharArray[0][2].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[0][2].totalGames)
                )

            totalBtnSolo.setTypeface(totalBtnSolo.typeface, Typeface.BOLD)
            totalBtnDuo.setTypeface(totalBtnDuo.typeface, Typeface.NORMAL)
            totalBtnSquad.setTypeface(totalBtnSquad.typeface, Typeface.NORMAL)
            totalBtnSolo.setTextColor(Color.parseColor("#E8B32C"))
            totalBtnDuo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnSquad.setTextColor(Color.parseColor("#5A5858"))
            centerSoloUnderBar.visibility = View.VISIBLE
            centerDuoUnderBar.visibility = View.GONE
            centerSquadUnderBar.visibility = View.GONE
        }

        // total - duo 버튼 리스너
        totalBtnDuo.setOnClickListener {

            val duo_charcode1 = top3CharArray[1][0].characterCode
            val duo_charcode2 = top3CharArray[1][1].characterCode
            val duo_charcode3 = top3CharArray[1][2].characterCode
            val duo_newFrag = supportFragmentManager.findFragmentById(R.id.total_summary) as total_summary_fragment

            if(duo_charcode1 != 0)
                duo_newFrag.changeC1(
                        charImageArray[duo_charcode1-1],
                        charNameArray[duo_charcode1-1],
                        String.format("AVG %d %s", top3CharArray[1][0].averageRank, when(top3CharArray[1][0].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[1][0].totalGames)
                )
            if(duo_charcode2 != 0)
                duo_newFrag.changeC2(
                        charImageArray[duo_charcode2-1],
                        charNameArray[duo_charcode2-1],
                        String.format("AVG %d %s", top3CharArray[1][1].averageRank, when(top3CharArray[1][1].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[1][1].totalGames)
                )
            if(duo_charcode3 != 0)
                duo_newFrag.changeC3(
                        charImageArray[duo_charcode3-1],
                        charNameArray[duo_charcode3-1],
                        String.format("AVG %d %s", top3CharArray[1][2].averageRank, when(top3CharArray[1][2].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[1][2].totalGames)
                )
            totalBtnSolo.setTypeface(totalBtnSolo.typeface, Typeface.NORMAL)
            totalBtnDuo.setTypeface(totalBtnDuo.typeface, Typeface.BOLD)
            totalBtnSquad.setTypeface(totalBtnSquad.typeface, Typeface.NORMAL)
            totalBtnSolo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnDuo.setTextColor(Color.parseColor("#E8B32C"))
            totalBtnSquad.setTextColor(Color.parseColor("#5A5858"))
            centerSoloUnderBar.visibility = View.GONE
            centerDuoUnderBar.visibility = View.VISIBLE
            centerSquadUnderBar.visibility = View.GONE
        }

        // total - squad 버튼 리스너
        totalBtnSquad.setOnClickListener {

            val squad_charcode1 = top3CharArray[2][0].characterCode
            val squad_charcode2 = top3CharArray[2][1].characterCode
            val squad_charcode3 = top3CharArray[2][2].characterCode
            val squad_newFrag = supportFragmentManager.findFragmentById(R.id.total_summary) as total_summary_fragment

            if(squad_charcode1 != 0)
                squad_newFrag.changeC1(
                        charImageArray[squad_charcode1-1],
                        charNameArray[squad_charcode1-1],
                        String.format("AVG %d %s", top3CharArray[2][0].averageRank, when(top3CharArray[2][0].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[2][0].totalGames)
                )
            if (squad_charcode2 != 0)
                squad_newFrag.changeC2(
                        charImageArray[squad_charcode2-1],
                        charNameArray[squad_charcode2-1],
                        String.format("AVG %d %s", top3CharArray[2][1].averageRank, when(top3CharArray[2][1].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[2][1].totalGames)
                )
            if (squad_charcode3 != 0)
                squad_newFrag.changeC3(
                        charImageArray[squad_charcode3-1],
                        charNameArray[squad_charcode3-1],
                        String.format("AVG %d %s", top3CharArray[2][2].averageRank, when(top3CharArray[2][2].averageRank){ 1 -> {"st"} 2 -> {"nd"} 3 -> {"rd"} else -> {"th"}}),
                        String.format("%d 게임", top3CharArray[2][2].totalGames)
                )

            totalBtnSolo.setTypeface(totalBtnSolo.typeface, Typeface.NORMAL)
            totalBtnDuo.setTypeface(totalBtnDuo.typeface, Typeface.NORMAL)
            totalBtnSquad.setTypeface(totalBtnSquad.typeface, Typeface.BOLD)
            totalBtnSolo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnDuo.setTextColor(Color.parseColor("#5A5858"))
            totalBtnSquad.setTextColor(Color.parseColor("#E8B32C"))
            centerSoloUnderBar.visibility = View.GONE
            centerDuoUnderBar.visibility = View.GONE
            centerSquadUnderBar.visibility = View.VISIBLE
        }

    }
}