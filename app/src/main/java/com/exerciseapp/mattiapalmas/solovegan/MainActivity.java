package com.exerciseapp.mattiapalmas.solovegan;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements ScanFragment.OnFragmentInteractionListener, TravelFragment.OnFragmentInteractionListener, ComponentsFragment.OnFragmentInteractionListener, ENumberFragment.OnFragmentInteractionListener, BrandsFragment.OnFragmentInteractionListener {

    LinearLayout scanLayout, travelLayout, componentsLayout, mainLayout, componentSelectLayout, eNumberLayout, brandsLayout;
    TextView descriptionTextView;
    final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    DatabaseHelper myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ScanFragment scanFragment = new ScanFragment();
        fragmentTransaction.replace(R.id.fragment_container, scanFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        initVariables();
        prepareMenuClickable();
        setUpDataBase();
    }

    private void initVariables() {
        scanLayout = findViewById(R.id.scan_menu_layout);
        travelLayout = findViewById(R.id.travel_menu_layout);
        componentsLayout = findViewById(R.id.components_menu_layout);
        eNumberLayout = findViewById(R.id.e_numbers_menu_layout);
        brandsLayout = findViewById(R.id.brands_menu_layout);

        componentSelectLayout = findViewById(R.id.component_select_layout);
        mainLayout = findViewById(R.id.main_layout);
        myDataBase = new DatabaseHelper(this);

        descriptionTextView = findViewById(R.id.description_text_view);
    }

    private void prepareMenuClickable() {


        scanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ScanFragment scanFragment = new ScanFragment();
                fragmentTransaction.replace(R.id.fragment_container, scanFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setColorForSelectedMenu(0);
            }
        });


        componentsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ComponentsFragment componentsFragment = new ComponentsFragment();
                fragmentTransaction.replace(R.id.fragment_container, componentsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setColorForSelectedMenu(1);
            }
        });

        travelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TravelFragment travelFragment = new TravelFragment();
                fragmentTransaction.replace(R.id.fragment_container, travelFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setColorForSelectedMenu(2);
            }
        });

        eNumberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ENumberFragment eNumberFragment = new ENumberFragment();
                fragmentTransaction.replace(R.id.fragment_container, eNumberFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setColorForSelectedMenu(3);
            }
        });

        brandsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BrandsFragment brandsFragment = new BrandsFragment();
                fragmentTransaction.replace(R.id.fragment_container, brandsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setColorForSelectedMenu(4);
            }
        });
    }

    private void setColorForSelectedMenu(int layout) {
        switch (layout){
            case 0 :
                scanLayout.setBackgroundColor(getResources().getColor(R.color.kakichiaro));
                componentsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                travelLayout.setBackgroundColor(getResources().getColor(R.color.white));
                eNumberLayout.setBackgroundColor(getResources().getColor(R.color.white));
                brandsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case 1 :
                scanLayout.setBackgroundColor(getResources().getColor(R.color.white));
                componentsLayout.setBackgroundColor(getResources().getColor(R.color.kakichiaro));
                travelLayout.setBackgroundColor(getResources().getColor(R.color.white));
                eNumberLayout.setBackgroundColor(getResources().getColor(R.color.white));
                brandsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case 2 :
                scanLayout.setBackgroundColor(getResources().getColor(R.color.white));
                componentsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                travelLayout.setBackgroundColor(getResources().getColor(R.color.kakichiaro));
                eNumberLayout.setBackgroundColor(getResources().getColor(R.color.white));
                brandsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case 3 :
                scanLayout.setBackgroundColor(getResources().getColor(R.color.white));
                componentsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                travelLayout.setBackgroundColor(getResources().getColor(R.color.white));
                eNumberLayout.setBackgroundColor(getResources().getColor(R.color.kakichiaro));
                brandsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case 4 :
                scanLayout.setBackgroundColor(getResources().getColor(R.color.white));
                componentsLayout.setBackgroundColor(getResources().getColor(R.color.white));
                travelLayout.setBackgroundColor(getResources().getColor(R.color.white));
                eNumberLayout.setBackgroundColor(getResources().getColor(R.color.white));
                brandsLayout.setBackgroundColor(getResources().getColor(R.color.kakichiaro));
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        if (componentSelectLayout.getVisibility() == View.VISIBLE) {
            mainLayout.setVisibility(View.VISIBLE);
            componentSelectLayout.setVisibility(View.GONE);
            descriptionTextView.setText("");
            descriptionTextView.scrollTo(1,1);
            return;
        }
        super.onBackPressed();
    }

    private void setUpDataBase() {
        Cursor res = myDataBase.getAllData();
        if (res.getCount() == 0) {
            // list of fabrics
            myDataBase.insertData("Aba", "a fabric woven from goat and camel hair.", false, true, false, "fabrics");
            myDataBase.insertData("Acrylic", "a synthetic fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Aertex", "a trademark for a loosely woven cotton fabric that is used to make shirts and underwear.", true, false, false, "fabrics");
            myDataBase.insertData("alpaca", "a thin glossy fabric made of the wool of the alpaca, or a rayon or cotton imitation.", false, true, false, "fabrics");
            myDataBase.insertData("Baize", "a bright green fabric napped to resemble felt; used to cover gaming tables.", false, false, true, "fabrics");
            myDataBase.insertData("Batiste", "a thin plain-weave cotton or linen fabric; used for shirts or dresses.", true, false, false, "fabrics");
            myDataBase.insertData("Broadcloth", "is a dense woollen cloth. Modern broadcloth can be composed of cotton, silk, or polyester, but traditionally broadcloth was made solely of wool.", false, false, true, "fabrics");
            myDataBase.insertData("Brocade", "thick heavy expensive material with a raised pattern, a class of richly decorative shuttle-woven fabrics, often made in colored silks and with or without sulaimangold and silver threads.", false, true, false, "fabrics");
            myDataBase.insertData("Buckram", "a coarse cotton fabric stiffened with glue; used in bookbinding and to stiffen clothing.", true, false, false, "fabrics");
            myDataBase.insertData("Bunting", "a loosely woven fabric used for flags, etc.", true, false, false, "fabrics");
            myDataBase.insertData("Calico", "a plain-woven textile made from unbleached, and often not fully processed, cotton.", true, false, false, "fabrics");
            myDataBase.insertData("Cambric", "a finely woven white linen.", true, false, false, "fabrics");
            myDataBase.insertData("Camelhair", "a soft tan cloth made with the hair of a camel.", false, true, false, "fabrics");
            myDataBase.insertData("Camlet", "a fabric of Asian origin; originally made of silk and camel’s hair.", false, true, false, "fabrics");
            myDataBase.insertData("Canvas", "heavy closely woven fabric (used for clothing or chairs or sails or tents).", true, false, false, "fabrics");
            myDataBase.insertData("Cashmere", "a soft fabric made from the wool of the Cashmere goat", false, true, false, "fabrics");
            myDataBase.insertData("Cerecloth (altar cloth)", "a waterproof waxed cloth once used as a shroud (also called altar cloth, used in churches).", false, true, false, "fabrics");
            myDataBase.insertData("Challis", "a soft lightweight fabric (usually printed)A soft, lightweight, usually printed fabric made of wool, cotton, or rayon.", false, false, true, "fabrics");
            myDataBase.insertData("Chambray or cambric", "Initially made of linen (flax), then cotton in the 19th century, it is also called batiste. Cambric is used for linens, shirtings, handkerchieves and as fabric for lace and needlework.", true, false, false, "fabrics");
            myDataBase.insertData("Chenille", "a heavy fabric woven with chenille cord; used in rugs and bedspreads, commonly manufactured from cotton, but can also be made using acrylic, rayon and olefin.", true, false, false, "fabrics");
            myDataBase.insertData("Chiffon", "a sheer fabric of silk or rayon.", false, false, true, "fabrics");
            myDataBase.insertData("Chino", "a coarse twilled cotton fabric used for uniforms.", true, false, false, "fabrics");
            myDataBase.insertData("Chintz", "a brightly printed and glazed cotton fabric", true, false, false, "fabrics");
            myDataBase.insertData("Cord, Corduroy", "a cut pile fabric with vertical ribs; usually made of cotton.", true, false, false, "fabrics");
            myDataBase.insertData("Cotton", "fabric woven from cotton fibers.", true, false, false, "fabrics");
            myDataBase.insertData("Cotton flannel, Canton flannel", "a stout cotton fabric with nap on only one side.", true, false, false, "fabrics");
            myDataBase.insertData("Crepe, Crape, Crepe de Chine", "a silk, wool, or polyester fabric of a gauzy texture, having a peculiar crisp or crimpy appearance.", false, false, true, "fabrics");
            myDataBase.insertData("Cretonne", "an unglazed heavy fabric; brightly printed; used for slipcovers and draperies , from hemp or linen", true, false, false, "fabrics");
            myDataBase.insertData("Damask", "a fabric of linen or cotton or silk or wool with a reversible pattern woven into it.", false, false, true, "fabrics");
            myDataBase.insertData("Denim, Dungaree, Jean", "a coarse durable twill-weave cotton fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Diaper", "a fabric (usually cotton or linen) with a distinctive woven pattern of small repeated figures.", true, false, false, "fabrics");
            myDataBase.insertData("Dimity", "a strong cotton fabric with a raised pattern; used for bedcovers and curtains", true, false, false, "fabrics");
            myDataBase.insertData("Doeskin", "a fine smooth soft woolen fabric.", false, true, false, "fabrics");
            myDataBase.insertData("Duck", "a heavy cotton fabric of plain weave; used for clothing and tents.", true, false, false, "fabrics");
            myDataBase.insertData("Duffel, duffle", "a coarse heavy woolen fabric.", false, true, false, "fabrics");
            myDataBase.insertData("Elastic", "an elastic fabric made of yarns containing an elastic material.", true, false, false, "fabrics");
            myDataBase.insertData("Etamine, Etamin", "a soft cotton or worsted fabric with an open mesh; used for curtains or clothing etc.", true, false, false, "fabrics");
            myDataBase.insertData("Faille", "a ribbed woven fabric of silk or rayon or cotton.", false, false, true, "fabrics");
            myDataBase.insertData("Felt", "a fabric made of compressed matted animal fibers.", false, true, false, "fabrics");
            myDataBase.insertData("Fiber, Fibre, Vulcanized fiber", "a leatherlike material made by compressing layers of paper or cloth.", true, false, false, "fabrics");
            myDataBase.insertData("Flannel", "a soft light woolen fabric; used for clothing.", false, true, false, "fabrics");
            myDataBase.insertData("Flannelette", "a cotton fabric imitating flannel.", true, false, false, "fabrics");
            myDataBase.insertData("Fleece", "a soft bulky fabric with deep pile; used chiefly for clothing.", true, false, false, "fabrics");
            myDataBase.insertData("Foulard", "a light plain-weave or twill-weave silk or silklike fabric (usually with a printed design).", false, false, true, "fabrics");
            myDataBase.insertData("Frieze", "a heavy woolen fabric with a long nap.", false, true, false, "fabrics");
            myDataBase.insertData("Fustian", "a strong cotton and linen fabric with a slight nap.", true, false, false, "fabrics");
            myDataBase.insertData("Gabardine", "a tough, tightly woven fabric used to make suits, overcoats, trousers, uniforms, windbreakers, and other garments, traditionally worsted wool, but may also be cotton, texturized polyester, or a blend.", false, false, true, "fabrics");
            myDataBase.insertData("Georgette", "a thin silk dress material.", false, true, false, "fabrics");
            myDataBase.insertData("Gingham", "a clothing fabric in a plaid weave.", true, false, false, "fabrics");
            myDataBase.insertData("Grogram", "a coarse fabric of silk mixed with wool or mohair and often stiffened with gum.", false, true, false, "fabrics");
            myDataBase.insertData("Grosgrain", "a silk or silklike fabric with crosswise ribs.", false, false, true, "fabrics");
            myDataBase.insertData("Haircloth, hair", "cloth woven from horsehair or camelhair; used for upholstery or stiffening in garments.", false, true, false, "fabrics");
            myDataBase.insertData("Horsehair", "fabric made from horsehair fibers; used for upholstery.", false, true, false, "fabrics");
            myDataBase.insertData("Jersey", "a slightly elastic machine-knit fabric , originally made of wool, but is now made of wool, cotton, and synthetic fibres.", false, false, true, "fabrics");
            myDataBase.insertData("Khaki", "a sturdy twilled cloth of a yellowish brown color used especially for military uniforms, usually made from cotton and linen.", true, false, false, "fabrics");
            myDataBase.insertData("Lame", "a fabric interwoven with threads of metal.", true, false, false, "fabrics");
            myDataBase.insertData("Leather", "a durable and flexible material created by the tanning of putrescible animal rawhide and skin, primarily cattlehide (cows).", false, true, false, "fabrics");
            myDataBase.insertData("Leatherette, Imitation leather", "fabric made to look like leather.", true, false, false, "fabrics");
            myDataBase.insertData("Linen", "a fabric woven with fibers from the flax plant.", true, false, false, "fabrics");
            myDataBase.insertData("Linsey-woolsey", "a rough fabric of linen warp and wool or cotton woof.", false, false, true, "fabrics");
            myDataBase.insertData("Lint", "cotton or linen fabric with the nap raised on one side; used to dress wounds.", true, false, false, "fabrics");
            myDataBase.insertData("Lisle", "a fabric woven with lisle thread (a type of cotton).", false, true, false, "fabrics");
            myDataBase.insertData("Mackinaw", "a heavy woolen cloth heavily napped and felted, often with a plaid design.", false, true, false, "fabrics");
            myDataBase.insertData("Mackintosh, Macintosh", "a lightweight waterproof (usually rubberized) fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Madras", "a light patterned cotton cloth.", true, false, false, "fabrics");
            myDataBase.insertData("Marseille", "strong cotton fabric with a raised pattern; used for bedspreads.", true, false, false, "fabrics");
            myDataBase.insertData("Mohair", "fabric made with yarn made from the silky hair of the Angora goat.", false, true, false, "fabrics");
            myDataBase.insertData("Moire, Watered-silk", "silk fabric with a wavy surface pattern.", false, true, false, "fabrics");
            myDataBase.insertData("Moleskin", "a durable cotton fabric with a velvety nap.", true, false, false, "fabrics");
            myDataBase.insertData("Monk’s cloth", "a heavy cloth in basket weave , made from cotton.", true, false, false, "fabrics");
            myDataBase.insertData("Moquette", "a thick velvety synthetic fabric used for carpets and soft upholstery.", true, false, false, "fabrics");
            myDataBase.insertData("Moreen", "a heavy fabric of wool (or wool and cotton) used especially in upholstery.", false, true, false, "fabrics");
            myDataBase.insertData("Motley", "a multicolored woolen fabric woven of mixed threads in 14th to 17th century England.", false, true, false, "fabrics");
            myDataBase.insertData("Mousseline de sole", "a gauze-like fabric of silk or rayon.", false, false, true, "fabrics");
            myDataBase.insertData("Muslin", "plain-woven cotton fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Nankeen", "a kind of pale yellowish cloth, originally made at Nanjing from a yellow variety of cotton, but subsequently manufactured from ordinary cotton which is then dyed.", true, false, false, "fabrics");
            myDataBase.insertData("Ninon", "a fine strong sheer silky fabric made of silk or rayon or nylon.", false, false, true, "fabrics");
            myDataBase.insertData("Nylon", "a synthetic fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Oilcloth", "cloth treated on one side with a drying oil or synthetic resin.", true, false, false, "fabrics");
            myDataBase.insertData("Organdie, Organdy", "a sheer stiff muslin.", true, false, false, "fabrics");
            myDataBase.insertData("Organza", "a fabric made of silk or a silklike fabric that resembles organdy.", false, false, true, "fabrics");
            myDataBase.insertData("Orlon", "an acrylic fiber or the lightweight crease-resistant fabric made with Orlon yarns.", true, false, false, "fabrics");
            myDataBase.insertData("Paisley", "a soft wool fabric with a colorful swirled pattern of curved shapes.", false, true, false, "fabrics");
            myDataBase.insertData("Percale", "a fine closely woven cotton fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Pilot cloth", "a thick blue cloth used to make overcoats and coats for sailors etc.", true, false, false, "fabrics");
            myDataBase.insertData("Pique", "tightly woven fabric with raised cords (normally with cotton).", true, false, false, "fabrics");
            myDataBase.insertData("Plush", "a textile having a cut nap or pile the same as fustian or velvet. Originally the pile of plush consisted of mohair or worsted yarn, but now silk by itself or with a cotton backing is used for plush, the distinction from velvet being found in the longer and less dense pile of plush. Modern plush are commonly manufactured from synthetic fibres such as polyester.", false, false, true, "fabrics");
            myDataBase.insertData("Polyester", "any of a large class of synthetic fabrics.", true, false, false, "fabrics");
            myDataBase.insertData("Pongee", "a soft thin cloth woven from raw silk (or an imitation).", false, false, true, "fabrics");
            myDataBase.insertData("Rayon", "a synthetic silklike fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Rep, Repp", "a fabric with prominent rounded crosswise ribs, made of silk, wool, or cotton.", false, false, true, "fabrics");
            myDataBase.insertData("Russet", "a reddish brown homespun fabric , a coarse cloth made of wool and dyed with woad and madder to give it a subdued grey or brown shade.", false, true, false, "fabrics");
            myDataBase.insertData("Sarcenet, Sarsenet", "a fine soft silk fabric often used for linings.", false, true, false, "fabrics");
            myDataBase.insertData("Samite", "a heavy silk fabric (often woven with silver or gold threads); used to make clothing in the Middle Ages.", false, true, false, "fabrics");
            myDataBase.insertData("Sateen", "a cotton fabric with a satiny finish.", true, false, false, "fabrics");
            myDataBase.insertData("Satin", "a smooth fabric of silk or rayon; has a glossy face and a dull back.", false, false, true, "fabrics");
            myDataBase.insertData("Screening", "fabric of metal or plastic mesh.", true, false, false, "fabrics");
            myDataBase.insertData("Scrim", "a very light textile made from cotton, or sometimes flax. Its light weight and translucence means it is often used for making curtains. The fabric can also be used for bookbinding and upholstery.", true, false, false, "fabrics");
            myDataBase.insertData("Seersucker", "a thin, puckered, all-cotton fabric, commonly striped or checkered, used to make clothing for spring and summer wear.", true, false, false, "fabrics");
            myDataBase.insertData("Serge", "a twilled woolen fabric.", false, true, false, "fabrics");
            myDataBase.insertData("Shag", "a fabric with long coarse nap , a rug or carpet that has a deep pile, giving it a shaggy appearance.", false, true, false, "fabrics");
            myDataBase.insertData("Shantung", "a heavy silk fabric with a rough surface (or a cotton imitation).", false, false, true, "fabrics");
            myDataBase.insertData("Shark skin, Shagreen", "a type of rawhide consisting of rough untanned skin, formerly made from a horse’s back or that of an onager (wild ass). Shagreen is now commonly made of the skins of sharks and rays.", false, true, false, "fabrics");
            myDataBase.insertData("Silesie", "a sturdy twill-weave cotton fabric; used for pockets and linings.", true, false, false, "fabrics");
            myDataBase.insertData("Silk", "a fabric made from the fine threads produced by certain insect larvae.", false, true, false, "fabrics");
            myDataBase.insertData("Spandex", "an elastic synthetic fabric.", true, false, false, "fabrics");
            myDataBase.insertData("Sponge cloth", "any soft porous fabric (especially in a loose honeycomb weave).", false, false, true, "fabrics");
            myDataBase.insertData("Stammel", "a coarse woolen cloth formerly used for undergarments and usually dyed bright red.", false, true, false, "fabrics");
            myDataBase.insertData("Suede leather", "a type of leather with a napped finish, commonly used for jackets, shoes, shirts, purses, furniture and other items. Suede leather is made from the underside of the skin, primarily lamb, although goat, pig, calf and deer are commonly used.", false, true, false, "fabrics");
            myDataBase.insertData("Swan’s down", "soft woolen fabric used especially for baby clothes.", false, true, false, "fabrics");
            myDataBase.insertData("Taffeta", "a crisp, smooth plain woven fabric made from silk or synthetic fibres.", false, false, true, "fabrics");
            myDataBase.insertData("Tammy", "plain-woven (often glazed) fabric of wool or wool and cotton used especially formerly for linings and garments and curtains.", false, true, false, "fabrics");
            myDataBase.insertData("Tapa, tappa", "a paperlike cloth made in the South Pacific by pounding tapa bark.", true, false, false, "fabrics");
            myDataBase.insertData("Tapestry, Tapis", "a heavy textile with a woven design; used for curtains and upholstery, Most weavers use a naturally based warp thread such as linen or cotton. The weft threads are usually wool or cotton, but may include silk, gold, silver, or other alternatives.", false, false, true, "fabrics");
            myDataBase.insertData("Toweling", "any of various fabrics (linen or cotton) used to make towels.", true, false, false, "fabrics");
            myDataBase.insertData("Tweed", "thick woolen fabric used for clothing; originated in Scotland.", false, true, false, "fabrics");
            myDataBase.insertData("Ultrasuede", "a synthetic suede cloth.", true, false, false, "fabrics");
            myDataBase.insertData("Velcro", "nylon fabric used as a fastening.", true, false, false, "fabrics");
            myDataBase.insertData("Velour", "heavy fabric that resembles velvet , and it is usually made from cotton but can also be made from synthetic materials such as polyester.", true, false, false, "fabrics");
            myDataBase.insertData("Velvet", "a silky densely piled fabric with a plain back , it can be made from many different kinds of fibres, traditionally silk. Velvet made entirely from silk has market prices of several hundred US dollars per yard. Cotton can also be used, though this often results in a slightly less luxurious fabric. Velvet can also be made from fibers such as linen, mohair, and wool. More recently, synthetic velvets have been developed, mostly polyester, nylon, viscose, acetate, and mixtures of different synthetics, or synthetics and natural fibers (for example viscose mixed with silk). A small percentage of spandex is sometimes added to give stretch.", false, false, true, "fabrics");
            myDataBase.insertData("Velveteen", "a usually cotton fabric with a short pile imitating velvet.", true, false, false, "fabrics");
            myDataBase.insertData("Vicuna", "a soft wool fabric made from the fleece of the vicuna.", false, true, false, "fabrics");
            myDataBase.insertData("Viscose, Viscone rayon", "a rayon fabric made from viscose (cellulose xanthate) fibers.", true, false, false, "fabrics");
            myDataBase.insertData("Viyella", "a fabric made from a twilled mixture of cotton and wool.", false, true, false, "fabrics");
            myDataBase.insertData("Voile", "a light semitransparent , soft, sheer fabric, usually made of 100% cotton or cotton blends including linen or polyester.", true, false, false, "fabrics");
            myDataBase.insertData("Whipcord", "a strong worsted or cotton fabric made of hard-twisted yarns with a diagonal cord or rib.", false, false, true, "fabrics");
            myDataBase.insertData("Wincey", "a plain or twilled fabric of wool and cotton used especially for warm shirts or skirts and pajamas.", false, true, false, "fabrics");
            myDataBase.insertData("Wire cloth", "fabric woven of metallic wire.", true, false, false, "fabrics");
            myDataBase.insertData("Wool, Woolen, Woollen", "a fabric made from the hair of sheep.", false, true, false, "fabrics");
            myDataBase.insertData("Worsted", "a woolen fabric with a hard textured surface and no nap; woven of worsted yarns.", false, true, false, "fabrics");


            //List of food
            myDataBase.insertData("Acetate (B)", " Vitamin A", false, false, true, "food");
            myDataBase.insertData("Actinidin","enzyme derived from kiwi fruit used in the food industry.",true,false,false,"food");
            myDataBase.insertData("Adrenaline","comes from the adrenal glands of hogs, cattle and sheep",false,false,true,"food");
            myDataBase.insertData("Agar-agar (agar)","extracted from seaweeds",true,false,false,"food");
            myDataBase.insertData("Albumen/Albumin","a group of simple proteins composed of nitrogen, carbon, hydrogen, oxygen, and sulfur that are soluble in water. Albumen is usually derived from eggwhites (ovalbumin), but can also be found in plasma (serum albumin), milk (lactalbumin) and vegetables and fruits.",false,false,true,"food");
            myDataBase.insertData("Allantoin","can be extracted from urea (from the urine of most animals, including humans) or from herbs such as comfrey or uva ursi.",false,false,true,"food");
            myDataBase.insertData("Allura Red","Derived from either coal tar or petroleum. It is not derived from insects. ",false,false,true,"food");
            myDataBase.insertData("Aloe Vera","A compound expressed from the leaf of the aloe plant.",true,false,false,"food");
            myDataBase.insertData("Alpha hydroxy acids","naturally-occurring chemicals derived from fruit or milk.",false,false,true,"food");
            myDataBase.insertData("Aluminum Hydroxide","manufactured by dissolving bauxite in sodium hydroxide.",true,false,false,"food");
            myDataBase.insertData("Aluminum Sulfate","used in the purification of drinking water and in the paper manufacturing industry. Produced by adding aluminum hydroxide to sulfuric acid.",true,false,false,"food");
            myDataBase.insertData("Ambergris","morbid concretion obtained from the intestine of the sperm whale.",false,true,false,"food");
            myDataBase.insertData("Amino acids","'building blocks' of proteins.",false,false,true,"food");
            myDataBase.insertData("Amniotic fluid","fluid surrounding the fetus within the placenta.",false,true,false,"food");
            myDataBase.insertData("Amylase","enzyme derived from either animal (usually porcine pancreas), fungal, bacterial or plant source (barley malt).",false,false,true,"food");
            myDataBase.insertData("Anchovy","small fish of the herring family.",false,true,false,"food");
            myDataBase.insertData("Angora","fiber obtained from angora rabbits.",false,true,true,"food");
            myDataBase.insertData("Annatto","a vegetable dye from a tropical tree.",true,false,false,"food");
            myDataBase.insertData("Anthocyanins","water-soluble plant pigments.",true,false,false,"food");
            myDataBase.insertData("Arachidonic acid","liquid unsaturated fatty acid that can be found in the liver, brain, glands, and fat of animals.",false,true,false,"food");
            myDataBase.insertData("Artificial","product is made by humans from natural ingredients. Like synthetic products, it would not exist without human intervention.",false,false,true,"food");
            myDataBase.insertData("Ascorbic Acid","a water-soluble vitamin found in vegetables and fruits or made synthetically.",true,false,false,"food");
            myDataBase.insertData("Aspartame","an artificial sweetener known as NutraSweet prepared from aspartic acid and phenylalanine (vegan according to the NutraSweet Company).",true,false,false,"food");
            myDataBase.insertData("Aspartic Acid","Aminosuccinate acid. An amino acid occurring in animals and plants. Usually synthesized from glutamate for commercial purposes.",false,false,true,"food");
            myDataBase.insertData("Aspic","savory jelly derived from meat and fish.",false,true,false,"food");
            myDataBase.insertData("Astrakhan","skin of still born or very young lambs from a breed originating in Astrakhan, Russia.",false,true,false,"food");
            myDataBase.insertData("Baker's Yeast","the common name for yeast used as a leavening agent in bakery products.",true,false,false,"food");
            myDataBase.insertData("Bauxite","an aluminum ore, which is the main commercial source of aluminum.",true,false,false,"food");
            myDataBase.insertData("Bee pollen","microsporic grains in seed plants gathered by bees then collected from the legs of bees.",false,true,false,"food");
            myDataBase.insertData("Beeswax","wax usually obtained from melting honeycomb with boiling water, straining it, and cooling it. Can be manufactured synthetically.",false,false,true,"food");
            myDataBase.insertData("Beet Sugar","sugar derived from sugar beets.",true,false,false,"food");
            myDataBase.insertData("Benzoic Acid","produced by the oxidation of toluene with an oxygen-containing gas in the presence of a heavy metal oxidation catalyst.",true,false,false,"food");
            myDataBase.insertData("Beta Carotene"," the carotene that's important in the diet as a precursor of vitamin A. It is used as a food coloring. Note: some manufacturers use gelatin as a stabilizer for beta carotene, to help it disperse in liquids.The gelatin won't necessarily be listed in the ingredient list of the product.",true,false,false,"food");
            myDataBase.insertData("Betatene","trade name for a naturally occurring blend of carotenes, including beta carotene. It is derived from the sea algae Dunaliella salina.",true,false,false,"food");
            myDataBase.insertData("Biotin","a whitish crystlalline powder, also known as Vitamin H, Vitamin B Factor or Vitamin B7. It can be derived from animal cells, milk or plant sources (like fruits) or produced synthetically.",false,false,true,"food");
            myDataBase.insertData("Bone/Bonemeal","animal bone",false,true,false,"food");
            myDataBase.insertData("Bone char(coal)/Boneblack","animal bone ash. Black residue from bones calcined in closed vessels. Used especially as a pigment or as a decolorizing absorbent in sugar manufacturing.",false,true,false,"food");
            myDataBase.insertData("Bone phosphate","manufactured from animal bones.",false,true,false,"food");
            myDataBase.insertData("Bonito","smaller relative of the tuna fish. It's used as an ingredient in Japanese cuisine.",false,true,false,"food");
            myDataBase.insertData("Brawn","boiled meat, ears and tongue of pig",false,true,false,"food");
            myDataBase.insertData("Brewer's Yeast"," live yeast used in beer brewing or deactivated yeast obtained as a by-product of beer brewing and used as a nutritional yeast product.",true,false,false,"food");
            myDataBase.insertData("Brilliant Blue FCF"," A synthetic dye derived from coal tar.",false,false,true,"food");
            myDataBase.insertData("Bristle","stiff animal hair, usually from pigs.",false,true,false,"food");
            myDataBase.insertData("Bromelain","enzyme derived from the fruit, stem and leaves of the pineapple plant.",true,false,false,"food");
            myDataBase.insertData("Butane","a gaseous component of natural gas. It's extracted during the production of petroleum products like gasoline or produced from crude oil.",true,false,false,"food");
            myDataBase.insertData("Calcium Carbonate","tasteless, odorless powder that occurs naturally in marble, limestone, coral, eggshells, pearls or oyster shells.",false,false,true,"food");
            myDataBase.insertData("Calcium Chloride","odorless white to off-white granules, powder or liquid. Produced in a variety of ways, including treating limestone with hydrochloric acid, combining limestone with a sodium chloride solution and by concentrating and purifying naturally occurring brines from salt lakes and salt deposits. Has many uses including additive for foods, deicing agent for sidewalks and roads, water treatment.",true,false,false,"food");
            myDataBase.insertData("Calcium Disodium EDTA"," a synthetic preservative used to prevent crystal formation and to retard color loss. Has caused health problems and is banned in Australia and certain other countries.",true,false,false,"food");
            myDataBase.insertData("Calcium Hydroxide","also known as slaked lime. Used as acidity regulator in drinks and frozen foods or as a preservative. It's produced commercially by treating lime with water or by mixing calcium chloride and sodium hydroxide.",true,false,false,"food");
            myDataBase.insertData("Calcium Lactate","the calcium salt of Lactic Acid.",false,false,true,"food");
            myDataBase.insertData("Calcium Phosphate","(Monobasic, Dibasic and Tribasic) a mineral salt found in rocks and bones. Used as an anti-caking agent in cosmetics and food, mineral supplement, abrasive in toothpaste and jelling agent. Also known as calcium rock.",false,false,true,"food");
            myDataBase.insertData("Calcium Stearate","mineral calcium with stearic acid.",false,false,true,"food");
            myDataBase.insertData("Calcium stearoyl-2-lactylate"," the calcium salt of the stearic acid ester of lactyl lactate.",false,false,true,"food");
            myDataBase.insertData("Candelilla wax","a vegetable wax obtained from candelilla plants.",true,false,false,"food");
            myDataBase.insertData("Cane Sugar"," sugar obtained from sugarcane. In some countries (like the U.S.), cane sugar is often processed through boneblack.",false,false,true,"food");
            myDataBase.insertData("Capiz","shell.",false,true,false,"food");
            myDataBase.insertData("Caramel","used as a coloring. It is manufactured by heating carbohydrates with or without acids or alkalis. Possible carbohydrates used are corn, beet sugar, cane sugar, wheat or potatoes. The great majority of caramel is derived from corn and will be vegan. However, some caramel is derived from cane sugar and not necessarily vegan.",false,false,true,"food");
            myDataBase.insertData("Carbamide","A waste product of digested protein filtered out by the kidneys and excreted from the body in urine. Commercially it is almost always produced from synthetic ammonia and carbon dioxide. It is rarely produced from animal urine.",false,false,true,"food");
            myDataBase.insertData("Carbon Black","derived from either burnt vegetable matter, incomplete combustion of natural gas, activated charcoal, bones, blood, meat or various fats oils and resins.",false,false,true,"food");
            myDataBase.insertData("Carbonic Acid","a weak acid formed when carbon dioxide combines with water.",true,false,false,"food");
            myDataBase.insertData("Carmine/Carminic acid","red pigment extracted from the crushed carcasses of the female cochineal insect, a cactus-feeding scale insect.",false,true,false,"food");
            myDataBase.insertData("Carnauba wax","wax obtained from the leaves of the carnauba palm.",true,false,false,"food");
            myDataBase.insertData("Carotene","red-orange pigment found in plants and fruits, consisting of  alpha carotene, beta carotene and gamma-carotene It can be produced synthetically, derived from carrots or sea algae.red-orange pigment found in plants and fruits, consisting of  alpha carotene, beta carotene and gamma-carotene It can be produced synthetically, derived from carrots or sea algae.",true,false,false,"food");
            myDataBase.insertData("Carrageenan","extracted from various red algae and especially Irish Moss.",true,false,false,"food");
            myDataBase.insertData("Casein","milk protein.",false,true,false,"food");
            myDataBase.insertData("Cashmere","fine wool from the cashmere goat and wild goat of Tibet",false,true,false,"food");
            myDataBase.insertData("Castor/Castoreum","Obtained from the anal scent gland of the beaver.",false,true,false,"food");
            myDataBase.insertData("Castor oil","A vegetable oil expressed from the castor bean.",true,false,false,"food");
            myDataBase.insertData("Catalase","enzyme that decomposes hydrogen peroxide into water and oxygen. It is derived from cattle liver or fungus and used in the food industry.",false,false,true,"food");
            myDataBase.insertData("Catgut","dried and twisted intestines of the sheep or horse.",false,true,false,"food");
            myDataBase.insertData("Caviar(e)","roe of the sturgeon and other fish.",false,true,false,"food");
            myDataBase.insertData("Cellulose","the principal component of the fiber of plants. Cellulose is usually obtained from wood pulp or cotton (which contains about 90% cellulose)",true,false,false,"food");
            myDataBase.insertData("Cetyl alcohol","found in Spermaceti or synthetic.",false,false,true,"food");
            myDataBase.insertData("Cetyl palmitate","found in Spermaceti or synthetic.",false,false,true,"food");
            myDataBase.insertData("Chalk","tasteless, odorless powder that occurs naturally in marble, limestone, coral, eggshells, pearls or oyster shells.",false,false,true,"food");
            myDataBase.insertData("Charcoal","charred bone or wood",false,false,true,"food");
            myDataBase.insertData("Chitin","organic base of the hard parts of insects and crustacea e.g. shrimps, crabs.",false,true,false,"food");
            myDataBase.insertData("Chamois","soft leather from the skin of the chamois antelope, sheep, goats, deer etc.",false,true,false,"food");
            myDataBase.insertData("Cholecalciferol","Vitamin D3,vitamin usually derived from animal sources like lanolin, milk, egg yolk and fish liver oil. Can also be derived from microbial or synthetic sources. Please note that synthetic vitamin D3 can have an animal ingredient as their starting raw material.",false,true,false,"food");
            myDataBase.insertData("Cholesterol"," a steroid alcohol occurring in all animal fats and oils, nervous tissue, egg yolk and blood.",false,true,false,"food");
            myDataBase.insertData("Chondroitin","used in products designed to help alleviate the effects of osteoarthritis. Produced synthetically or derived from the cartilage of cows, pigs, sharks, fish or birds.",false,false,true,"food");
            myDataBase.insertData("Chymosin"," R ennin, enzyme found in rennet. It is used to split the casein molecule during cheese making to clot milk and turn it into curds and whey.",false,false,true,"food");
            myDataBase.insertData("Chymotrypsin","enzyme primarily derived from ox pancreas.",false,true,false,"food");
            myDataBase.insertData("Cinnamic Acid","obtained from cinnamon leaves, coca leaves, balsams like storax or isolated from a wood-rotting fungus. Can be made synthetically.",true,false,false,"food");
            myDataBase.insertData("Citric Acid","derived from citrus fruits and since the 1920s commercially produced by fermenting sugar solutions with the microorganism Aspergillus niger. The main raw materials used in the production are corn-derived sucrose and molasses.",true,false,false,"food");
            myDataBase.insertData("Civet","substance painfully scraped from glands in the anal pouch of the civet cat.",false,false,true,"food");
            myDataBase.insertData("Coal tarthick liquid or semisolid tar obtained from bituminous coal (= soft coal),","",true,false,false,"food");
            myDataBase.insertData("Cochineal (Carmine, Carminic acid, Natural Red 4)","red pigment extracted from the crushed carcasses of the female cochineal insect, a cactus-feeding scale insect.",false,true,false,"food");
            myDataBase.insertData("Cod liver oil","oil extracted from the liver of cod and related fish.",false,true,false,"food");
            myDataBase.insertData("ColFlo 67","modified food starch derived from waxy maize. Used in frozen foods and canned products. Often labeled as 'Food Starch - Modified'.-",true,false,false,"food");
            myDataBase.insertData("Collagen","a protein found in most connective tissues, including bone, cartilage and skin. It is usually derived from cows or chickens.",false,true,false,"food");
            myDataBase.insertData("Collagen hydrolysate","a purified protein derived from animal sources. It's produced by breaking down gelatin to smaller protein fragments.",false,true,false,"food");
            myDataBase.insertData("Colors/Dyes","Can be from plant, animal and synthetic sources. Most FD&C and D&C colors are derived from coal tar. Coal tar in itself is considered a vegan product. However, coal tar derivatives cause frequent allergic reactions, like skin rashes and hives. It has also shown to cause cancer in animals. For this reason, colors and dyes are continuously tested on animals. That's why FD&C and D&C colors and dyes can generally not be considered vegan.",false,false,true,"food");
            myDataBase.insertData("Confectioner's Glaze"," also known as pharmaceutical glaze, confectioner's glaze, pure food glaze and natural glaze. It's made from various types of food grade shellac. It is also known as beetle juice, even though the lac insect it's derived from is a scale insect and not a beetle.",false,true,false,"food");
            myDataBase.insertData("Coral","hard calcareous substance consisting of the continuous skeleton secreted by coelenterate polyps for their support and habitation.",false,true,false,"food");
            myDataBase.insertData("Cornstarch/Corn starch","starch derived from dried corn kernels",true,false,false,"food");
            myDataBase.insertData("Corn Syrup"," a form of glucose made from corn starch. It is used as a sweetener.",true,false,false,"food");
            myDataBase.insertData("Corticosteroid/Cortisone","steroid hormones secreted by the adrenal cortex and their synthetic analogs.",false,false,true,"food");
            myDataBase.insertData("Cottonseed oil","fixed oil derived from the seeds of the cultivated varieties of the cotton plant.",true,false,false,"food");
            myDataBase.insertData("Crospovidone","a water-soluble polymer from synthetic origin. Used in products like pharmaceutical tablets, shampoo, toothpaste, batteries, paint and adhesives.",true,false,false,"food");
            myDataBase.insertData("Curcumin","Colorant derived from turmeric",true,false,false,"food");
            myDataBase.insertData("Cysteine, L-Form","amino acid that oxidizes to form cystine.",false,false,true,"food");
            myDataBase.insertData("Cystine","amino acid found in the hair protein keratin.",false,false,true,"food");
            myDataBase.insertData("Dashi (fish broth)","stock made from fish and seaweed. Used in Japanese cuisine.",false,true,false,"food");
            myDataBase.insertData("D&C Colors","colors that have been certified safe for use in drugs and cosmetics, but not in food by the FDA (the Food and Drug Administration of the U.S.). See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("Dextrin","prepared by heating dry starch  or starch treated with acids. Can be produced from the starch of corn, potatoes or rice.",true,false,false,"food");
            myDataBase.insertData("DiCalcium Phosphate","(Dibasic calcium phosphate, Dicalcium orthophosphate) the Dibasic form of calcium phosphate.",false,false,true,"food");
            myDataBase.insertData("Dihydroxyacetone","an emulsifier, humectant and fungicide which is obtained by the action of certain bacteria on glycerol.",false,false,true,"food");
            myDataBase.insertData("Direct Reduced Iron (DRI)","also known as Reduced Iron or Sponge Iron. It's used to fortify foods, like flour. It's produced from either iron ore or mill scale (the surface of hot rolled steel) by reduction with hydrogen or carbon monoxide.",true,false,false,"food");
            myDataBase.insertData("Disodium inosinate","flavor enhancer derived from meat, fish (sardines), vegetable or fungal source.",false,false,true,"food");
            myDataBase.insertData("Down","the undercoating of waterfowl (especially ducks and geese). See Feathers.",false,true,false,"food");
            myDataBase.insertData("Duodenum substances","from the digestive tracts of cows and pigs. Can be found in vitamin tablets.",false,true,false,"food");
            myDataBase.insertData("Elastin","protein uniting muscle fibers in meat.",false,true,false,"food");
            myDataBase.insertData("Emu oil","oil derived from the rendered fat of the emu, a large Australian flightless bird.",false,true,false,"food");
            myDataBase.insertData("Enzymes","protein molecules produced by living cells. They act as catalysts in living organisms, regulating the rate of chemical reactions without being changed in the process. Enzymes can be derived from animals, plants, bacteria, fungi and yeast. Most industrial enzymes consist of a mixture of enzymes. Enzymes include actinidin, amylase, bromelain, catalase, chymotrypsin, ficin, glucose isomerase, lactase, lipase, lipoxygenase, papain, rennet and trypsin.",false,false,true,"food");
            myDataBase.insertData("Ergocalciferol","Vitamin D2, vitamin usually derived from plant sterols or yeast. Can also be derived from animal fats.",false,false,true,"food");
            myDataBase.insertData("Erythorbic Acid","a food additive used as an antioxidant in processed foods. It's produced from sucrose.",true,false,false,"food");
            myDataBase.insertData("Estrogen/Estradiol","from cow ovaries and pregnant mares' urine.",false,true,false,"food");
            myDataBase.insertData("Fatty acids","organic compounds: saturated, polyunsaturated and unsaturated.",false,false,true,"food");
            myDataBase.insertData("FD&C Colors","colors that have been certified safe for use in food, drugs and cosmetics by the FDA (the Food and Drug Administration of the U.S.). See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("FD&C Blue 1","Brilliant Blue FCF. A synthetic dye derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("FD&C Red 40","Allura Red. Derived from either coal tar or petroleum. It is not derived from insects. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("FD&C Yellow 5","Tartrazine . Derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("FD&C Yellow 6","Monoazo. Derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("Feathers","epidermal appendage of a bird. Most feathers are removed from birds, especially geese, ducks or chickens, during slaughter as a by-product of the poultry industry. They can also be plucked from live birds, especially ducks and geese, who are bred for either meat, foie gras or egg laying and breeding.",false,false,true,"food");
            myDataBase.insertData("Felt","cloth made of wool, or of wool and fur or hair.",false,false,true,"food");
            myDataBase.insertData("Ferrous Lactate","derived from the direct action of lactic acid on iron fillings or from the interaction of calcium lactate with ferrous sulfate.",false,false,true,"food");
            myDataBase.insertData("Ferrous Sulfate","an astringent iron salt obtained in green crystalline form. Used as an antiseptic in cosmetics and in treating anemia in medicine.",true,false,false,"food");
            myDataBase.insertData("Ficin","enzyme derived from the latex of the fig tree.",true,false,false,"food");
            myDataBase.insertData("Folate","Vitamin B9,  used to fortify foods or as a supplement, especially for pregnant women. It's usually produced synthetically. Folate is the general term for Vitamin B9, whereas folic acid refers to the synthetic compound used in supplements and food fortification.",false,false,true,"food");
            myDataBase.insertData("Folic Acid"," a synthetic form of Vitamin B9.",true,false,false,"food");
            myDataBase.insertData("Fructose (Syrup)","fructose is a sugar that is found in many fruits, vegetables and honey. Commercial fructose and fructose-rich syrups are generally produced from starch (almost always corn starch). It is sometimes produced from inulin containing plants like chicory roots and Jerusalem artichoke tubers.",true,false,false,"food");
            myDataBase.insertData("Gelatin(e)","protein obtained by boiling animal skin, connective tissue or bones, usually from cows or pigs. It's an edible form of collagen. It is used as a gelling agent, stabilizer or thickener in cooking. It is also used in glues, photographic films, matches, sandpaper, certain soft drinks, playing cards, crepe paper and more.",false,true,false,"food");
            myDataBase.insertData("Glucono delta-lactone","also known as gluconolactone or GDL. A fine, white, acidic powder. It is usually produced by the oxidation of a glucose by microorganisms.",false,false,true,"food");
            myDataBase.insertData("Gluconolactone","also known as Glucono delta-lactone or GDL. A fine, white, acidic powder. It is usually produced by the oxidation of a glucose by microorganisms",false,false,true,"food");
            myDataBase.insertData("Glucose","a simple sugar usually produced by hydrolysis of a starch with mineral acids. Starches used include corn, rice, wheat, potato and arrowroot. It can also be produced synthetically or by adding crystallized cane sugar to a mixture of alcohol and acid. In some countries (like the U.S.) glucose is run through bone-char filters.",false,false,true,"food");
            myDataBase.insertData("Glucose isomerate","enzyme derived from the bacteria Streptomyces rubiginosus. It is used in the production of fructose syrups (including high fructose corn syrup) by changing glucose into fructose.",true,false,false,"food");
            myDataBase.insertData("Glucosamine","a dietary supplement used to aid in the relief of joint problems. Usually extracted from the tissues of shellfish. It can also be derived from corn or produced synthetically.",false,false,true,"food");
            myDataBase.insertData("Glycerin(e)/glycerol","a clear, colorless liquid which is a by-product of the soap-making process obtained by adding alkalies (solutions with a pH greater than 7) to fats and fixed oils. It may be derived from animal fats, synthesized from propylene or from fermentation of sugars. Vegetable glycerin is derived from vegetable fats.",false,false,true,"food");
            myDataBase.insertData("Glycine","an amino acid, obtainable by hydrolysis of proteins.",false,false,true,"food");
            myDataBase.insertData("Guanine/Pearl Essence","obtained from scales of fish.",false,true,false,"food");
            myDataBase.insertData("Guar Gum","Gum made from ground guar seeds.",true,false,false,"food");
            myDataBase.insertData("Gum Arabic/Gum Acacia","a natural gum produced by the acacia tree to heal its bark if damaged. It is used in cosmetics, candy, syrups and as glue.",true,false,false,"food");
            myDataBase.insertData("Hide","animal skin (raw or tanned).",false,true,false,"food");
            myDataBase.insertData("High Fructose Corn Syrup (HFCS)","produced by processing corn starch to yield glucose. This glucose is then treated with enzymes to increase the fructose content to make it sweeter. HFCS contains nearly equal amounts of fructose and glucose. It is almost always produced from genetically modified corn.",true,false,false,"food");
            myDataBase.insertData("Honey","food made by bees to feed themselves.",false,true,false,"food");
            myDataBase.insertData("Hydrochloric Acid","formed by heating hydrogen and chlorine gas to form hydrogen chloride gas, which is then absorbed in water.",true,false,false,"food");
            myDataBase.insertData("Hydroxypropyl cellulose","a derivative of cellulose. It is used as a thickener in food and for the coating of film and tablets.",true,false,false,"food");
            myDataBase.insertData("Hydroxypropyl methylcelluose (HPMC)","derived from alkali treated cellulose that is reacted with methyl chloride and propylene oxide. It can be used as an alternative to gelatin in hard capsules.",true,false,false,"food");
            myDataBase.insertData("Inositol","a sugar-like dietary supplement of the vitamin B complex. Unofficially referred to as vitamin B8. It is present in almost all plant and animal tissues. Commercially, it can be obtained from both animal and plant sources (especially corn).",false,false,true,"food");
            myDataBase.insertData("Insulin","pancreas of cattle, sheep or pigs.",false,false,true,"food");
            myDataBase.insertData("Inulin","a naturally occuring carbohydrate found in the roots and tubers of many plants. It is usually extracted from chicory root.",true,false,false,"food");
            myDataBase.insertData("Isinglass","very pure form of gelatin obtained from the air bladders of some freshwater fishes, especially the sturgeon.",false,true,false,"food");
            myDataBase.insertData("Katsuobushi (okaka)","essential ingredient in Japanese cuisine. It's made by drying either skipjack tuna or bonito fish into hard blocks and then creating flakes by using a shaving tool. It's used as a topping or filling in many Japanese dishes. It's the main ingredient of dashi.",false,true,false,"food");
            myDataBase.insertData("Keratin","protein found in hair, hoofs, horns and feathers.",false,true,false,"food");
            myDataBase.insertData("L-cysteine","derived from hair, both human and animal, or feathers. Can be synthetically produced from coal tar.",false,false,true,"food");
            myDataBase.insertData("L-cysteine hydrochloride","a compound produced from L-cysteine.",false,false,true,"food");
            myDataBase.insertData("Lactic acid","acid produced by the fermentation of whey, cornstarch, potatoes or molasses.",false,false,true,"food");
            myDataBase.insertData("Lactase","enzyme derived from fungus of yeast. It prevents lactose from being broken down into glucose and galactose. It is used in the dairy industry for people who are lactose intolerant.",true,false,false,"food");
            myDataBase.insertData("Lactoflavin"," Vitamin B2,used as a food coloring, to fortify foods or as a supplement. Produced synthetically or by a fermentation process with genetically modified Bacillus subtilis. It's usually vegan, but in rare cases it can be produced from animal sources like beef, especially when it's marked as being \"natural\".",false,false,true,"food");
            myDataBase.insertData("Lactose","milk sugar. A type of sugar only found in milk.",false,true,false,"food");
            myDataBase.insertData("Lanolin(e)","fat extracted from sheep's wool.",false,true,false,"food");
            myDataBase.insertData("Lard","fat surrounding the stomach and kidneys of the pig, sheep and cattle.",false,true,false,"food");
            myDataBase.insertData("Laurel","the fresh berries and leaf extract of the laurel tree.",true,false,false,"food");
            myDataBase.insertData("Lauric Acid","constituent of vegetable fats, especially coconut oil and laurel oil. Derivatives are used as a base in the manufacture of soaps, detergents and lauryl alcohol.",true,false,false,"food");
            myDataBase.insertData("Lauryl Alcohol","compound usually produced from coconut oil (which is naturally high in lauric acid) or from a petroleum based version of lauric acid.",true,false,false,"food");
            myDataBase.insertData("Leather","tanned hide (mostly from cattle but also sheep, pigs and goats etc)",false,true,false,"food");
            myDataBase.insertData("Lecithin","fatty substance found in nerve tissues, egg yolk, blood and other tissues. Mainly obtained commercially from soya bean, peanut and corn.",false,false,true,"food");
            myDataBase.insertData("Limestone","porous rock formed over thousands of years from the compression of shells and bones of marine animals.",true,false,false,"food");
            myDataBase.insertData("Lipase","enzyme from the stomachs, tongue glands of calves, kids and lambs. Can also be from derived from plants, fungus or yeast. It breaks down fat to glycerol and fatty acids.",false,false,true,"food");
            myDataBase.insertData("Lipoxygenase","enzyme derived from soybeans. It catalyzes the oxidation reaction. It is used in the baking industry to make bread appear more white.",true,false,false,"food");
            myDataBase.insertData("Lutein","substance of deep yellow color found in egg yolk. Obtained commercially from marigold.",false,false,true,"food");
            myDataBase.insertData("Magnesium stearate","ester of magnesium & stearic acid.",false,false,true,"food");
            myDataBase.insertData("Malic Acid","the natural acid present in fruits and vegetables. It's produced synthetically for use in food products, pharmaceuticals, paints, soaps and more.",true,false,false,"food");
            myDataBase.insertData("Maltodextrin","sugar obtained by hydrolysis of starch.",true,false,false,"food");
            myDataBase.insertData("Methanol","also known as methyl alcohol or wood alcohol. It used to be produced as a byproduct of the destructive distillation of wood. Currently it is usually produced synthetically.",true,false,false,"food");
            myDataBase.insertData("Methyl alcohol","also known as Methanol or wood alcohol. It used to be produced as a byproduct of the destructive distillation of wood. Currently it is usually produced synthetically.",true,false,false,"food");
            myDataBase.insertData("Mannitol (Mannite)","obtained from the dried sap of the flowering ash or from seaweed.",true,false,false,"food");
            myDataBase.insertData("Methyl cellulose (methylcellulose)","synthetically produced by heating cellulose with a solution of sodium hydroxide and treating it with methyl chloride. It is used as a thickener in sauces and dressings.",true,false,false,"food");
            myDataBase.insertData("Mentha (mint)"," derived from the flowering plants in the mint family.",true,false,false,"food");
            myDataBase.insertData("Metafolin","the brand name for a synthetically produced form of folate, which is chemically identical to the active form of folate found in food. It was created by the company Merck's.",true,false,false,"food");
            myDataBase.insertData("Methyl cinnamate","derived by heating methanol, cinnamic acid and sulfuric acid",true,false,false,"food");
            myDataBase.insertData("Methyl chloride (chloromethane)","a colorless, poisonous gas or liquid mostly of natural origin. It is released into the environment from the oceans and is used as a spray for pesticides in food storage and processing.",true,false,false,"food");
            myDataBase.insertData("Milk Sugar","A type of sugar only found in milk.",false,true,false,"food");
            myDataBase.insertData("Mink oil","from minks",false,true,false,"food");
            myDataBase.insertData("Modified (food) starch","starch which has been treated physically or chemically to modify one or more of its key physical or chemical properties. Physical modification can include drum-drying, extrusion, spray drying or heat/moisture treatment. Chemicals used to modify starch include propylene oxide, succinic anhydride, 1-octenyl succinic anhydride, aluminum sulfate or sodium hydroxide",true,false,false,"food");
            myDataBase.insertData("Mohair","cloth or yarn made from the hair of the angora goat",false,true,false,"food");
            myDataBase.insertData("Monoazo (Sunset Yellow FCF, Orange Yellow S)","Derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("Monocalcium Phosphate","(Monobasic calcium phosphate, Monocalcium orthophosphate) The Monobasic form of calcium phosphate.",false,false,true,"food");
            myDataBase.insertData("Mono-Diglycerides","Emulsifying agents in puddings, ice cream, peanut butter, bread etc. Can be derived from plants (oils from corn, peanuts or soybeans) or animals (cows and hogs)",false,false,true,"food");
            myDataBase.insertData("Monosodium glutamate (MSG)","produced from seaweed or by a bacterial fermentation process with molasses or starch and ammonium salts.",true,false,false,"food");
            myDataBase.insertData("Musk","substance secreted in a gland or sac by the male musk deer.",false,false,true,"food");
            myDataBase.insertData("Natural","ingredients are not synthetic or artificial, but extracted directly from either plants or animal products.",false,false,true,"food");
            myDataBase.insertData("Natural flavor","flavor derived from spices, fruits, fruit juices, vegetables, vegetable juices, plant materials, meat, seafood, poultry, eggs, dairy products or their fermentation products. The significant function of a natural flavor is not nutritional but flavoring.",false,false,true,"food");
            myDataBase.insertData("Natural Red 4","red pigment extracted from the crushed carcasses of the female cochineal insect, a cactus-feeding scale insect",false,true,false,"food");
            myDataBase.insertData("Niacin","used as a cholesterol treatment, to fortify foods or as a supplement. The largest commercial use is to fortify animal feed. It's usually produced synthetically. In rare cases it can be derived from animal sources.",false,false,true,"food");
            myDataBase.insertData("Nicotinic Acid","used as a cholesterol treatment, to fortify foods or as a supplement. The largest commercial use is to fortify animal feed. It's usually produced synthetically. In rare cases it can be derived from animal sources.",false,false,true,"food");
            myDataBase.insertData("NutraSweet"," an artificial sweetener prepared from aspartic acid and phenylalanine (vegan according to the NutraSweet Company)",true,false,false,"food");
            myDataBase.insertData("Nutritional Yeast","a commercial food product containing deactivated yeast. It's sold in the form of yellow powder or flakes. It's used as a condiment or food supplement. It's often vegan, but some brands use animal products like whey.",false,false,true,"food");
            myDataBase.insertData("Octinoxate","also known as octyl methoxycinnamate. Ester of m ethyl cinnamate.",true,false,false,"food");
            myDataBase.insertData("Octyl Methoxycinnamate","also known as Octinoxate. Ester of m ethyl cinnamate",true,false,false,"food");
            myDataBase.insertData("Oestrogen","female sex hormone from cow ovaries or pregnant mares' urine.",false,false,true,"food");
            myDataBase.insertData("Oleic acid","fatty acid occurring in animal and vegetable fats.",false,false,true,"food");
            myDataBase.insertData("Oleic alcohol","oleyl alcohol. A fatty alcohol derived from natural fats and oils, including beef fat and fish oil. It can also be manufactured from esters of oleic acid.",false,false,true,"food");
            myDataBase.insertData("Oleoic oil","liquid obtained from pressed tallow.",false,true,false,"food");
            myDataBase.insertData("Oleostearin","solid obtained from pressed tallow.",false,true,false,"food");
            myDataBase.insertData("Oleth-2 through 50","polyethylene glycol ethers of oleic alcohol",false,false,true,"food");
            myDataBase.insertData("Orange Yellow S","Derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("Oxybenzone","derived from isopropanol, which is prepared from propylene, obtained in the cracking of petroleum.",true,false,false,"food");
            myDataBase.insertData("Palmitate","salt or ester of palmitic acid",false,false,true,"food");
            myDataBase.insertData("Palmitic acid","fatty acid that occurs in palm oil and most other fats and oils.",false,false,true,"food");
            myDataBase.insertData("Panthenol/Dexpanthenol/Vitamin B Complex Factor","can come from animal, plant or synthetic sources",false,false,true,"food");
            myDataBase.insertData("Papain","enzyme derived from the unripe fruit of the papaya plant. It is used for clearing beverages, added to farina to reduce cooking time and used medically to prevent adhesions",true,false,false,"food");
            myDataBase.insertData("Paracasein","the chemical product of the action of rennin or pepsin on casein. To make hard cheese, paracasein is combined with soluble calcium salts to form calcium paracaseinate (cheese curd).",false,true,false,"food");
            myDataBase.insertData("Paraffin","waxy substance obtained from distillates of wood, coal, petroleum, or shale oil.",true,false,false,"food");
            myDataBase.insertData("Parchment","skin of the sheep or goat, dressed and prepared for writing etc.",false,false,true,"food");
            myDataBase.insertData("Pearl","concretion of layers of pain-dulling nacre formed around a foreign particle within the shell of various bivalve molluscs, principally the oyster.",false,true,false,"food");
            myDataBase.insertData("Pectin","a substance that is found in the primary cell walls and the non-woody parts of plants. Pectin is used as a gelling agent, thickener and stabilizer in food. Commercially, pectin is obtained mostly from dried citrus peels and apples as a by-product of juice production.",true,false,false,"food");
            myDataBase.insertData("PEG","PEG is the abbreviation of polyethylene glycol or polyoxyethylene glycol. They are polymeric forms of ethylene oxide. They can be either synthetic or derived from animal or vegetable sources.",false,false,true,"food");
            myDataBase.insertData("Pepsin","enzyme usually derived from the stomach of grown calves or sometimes pigs.",false,true,false,"food");
            myDataBase.insertData("Petroleum","an oily, flammable liquid composed of a complex mixture of hydrocarbons occurring in many places in the upper strata of the earth. A fossil fuel believed to have originated from both plant and animal sources millions of years ago.",true,false,false,"food");
            myDataBase.insertData("Pharmaceutical Glaze","also known as Resinous Glaze, confectioner's glaze, pure food glaze and natural glaze. It's made from various types of food grade shellac. It is also known as beetle juice, even though the lac insect it's derived from is a scale insect and not a beetle.",false,true,false,"food");
            myDataBase.insertData("Phenol","obtained from coal tar.",true,false,false,"food");
            myDataBase.insertData("Phosphoric Acid","an inorganic acid produced by reacting ground phosphate rock with sulfuric acid.",true,false,false,"food");
            myDataBase.insertData("Placenta","organ by which the fetus is attached to the umbilical cord.",false,true,false,"food");
            myDataBase.insertData("Polyethylene","a product of petroleum gas or dehydration of alcohol.",true,false,false,"food");
            myDataBase.insertData("Polyglycerol polyricinoleate","produced from castor oil and glycerol esters.",false,false,true,"food");
            myDataBase.insertData("Polysorbate 60","a condensate of sorbitol with stearic acid.",false,false,true,"food");
            myDataBase.insertData("Polysorbate 80","a condensate of sorbitol and oleic acid.",false,false,true,"food");
            myDataBase.insertData("Polyvinylpyrrolidone (PVP)","a water-soluble polymer from synthetic origin. Used in products like pharmaceutical tablets, shampoo, toothpaste, batteries, paint and adhesives.",true,false,false,"food");
            myDataBase.insertData("Polyoxyethylene (8) stearate","a mixture of stearate and ethylene oxide.",false,false,true,"food");
            myDataBase.insertData("Polyoxyethylene (40) stearate","a mixture of stearate and ethylene oxide produced by a reaction of ethylene oxide with stearic acid.",false,false,true,"food");
            myDataBase.insertData("Potassium Chloride","occurs naturally as the mineral sylvite and is found combined in many minerals and in brines and ocean water.",true,false,false,"food");
            myDataBase.insertData("Potassium Hydroxide","obtained commercially from the electrolysis of potassium chloride solution.",true,false,false,"food");
            myDataBase.insertData("Potassium Lactate","the potassium salt of lactic acid.",false,false,true,"food");
            myDataBase.insertData("Potassium Sorbate","sorbic acid potassium salt. Manufactured by neutralisation of sorbic acid with potassium hydroxide.",true,false,false,"food");
            myDataBase.insertData("Progesterone","sex hormone",false,false,true,"food");
            myDataBase.insertData("Propolis","bee glue. Used by bees to stop up crevices and fix combs to the hive.",false,true,false,"food");
            myDataBase.insertData("Propylene/propene","flammable gas obtained by cracking petroleum.",true,false,false,"food");
            myDataBase.insertData("Propylene glycol","1,2-propylene glycol; propane-1,2-diol. Manufactured by treating propylene with chlorinated water and treating it with sodium carbonate solution or by heating glycerol with sodium hydroxide and distilling the mixture.",false,false,true,"food");
            myDataBase.insertData("Propylene/propene oxide","a flammable liquid, derived from propylene.",true,false,false,"food");
            myDataBase.insertData("Quinoline Yellow","obtained by the interaction of aniline with acetaldehyde and formaldehyde or by distillation of coal tar , bones and alkaloids.",false,false,true,"food");
            myDataBase.insertData("Reduced Iron","also known as Direct Reduced Iron (DRI) or Sponge Iron. It's used to fortify foods, like flour. It's produced from either iron ore or mill scale (the surface of hot rolled steel) by reduction with hydrogen or carbon monoxide.",true,false,false,"food");
            myDataBase.insertData("Rennet","extract usually obtained from a newly-born calf stomach. Rennet contains the enzymes rennin and a little amount of pepsin. The older the veal calf, the more pepsin will be found in the rennet. Rennet can also be derived from synthetic sources or from bacteria and fungus",false,false,true,"food");
            myDataBase.insertData("Rennin","enzyme found in rennet. It is used to split the casein molecule during cheese making to clot milk and turn it into curds and whey.",false,false,true,"food");
            myDataBase.insertData("Red 40","Derived from either coal tar or petroleum. It is not derived from insects. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("Resinous Glaze","also known as pharmaceutical glaze, confectioner's glaze, pure food glaze and natural glaze. It's made from various types of food grade shellac. It is also known as beetle juice, even though the lac insect it's derived from is a scale insect and not a beetle.",false,true,false,"food");
            myDataBase.insertData("Reticulin","one of the structural elements (together with elastin and collagen) of skeletal muscle.",false,true,false,"food");
            myDataBase.insertData("Riboflavin","used as a food coloring, to fortify foods or as a supplement. Produced synthetically or by a fermentation process with genetically modified Bacillus subtilis. It's usually vegan, but in rare cases it can be produced from animal sources like beef, especially when it's marked as being \"natural\".",false,false,true,"food");
            myDataBase.insertData("Riboflavin-5-Phosphate","A more soluble form of riboflavin.",false,false,true,"food");
            myDataBase.insertData("Roe","eggs obtained from the abdomen of slaughtered female fish.",false,true,false,"food");
            myDataBase.insertData("Royal jelly","food on which bee larvae are fed and which causes them to develop into queen bees.",false,true,false,"food");
            myDataBase.insertData("Sable","fur from the sable marten, a small carnivorous mammal",false,true,false,"food");
            myDataBase.insertData("Salicylic Acid","derived from the leaves of wintergreen, meadowsweet, willow bark or other plants. It can also be produced synthetically by heating phenol with carbon dioxide.",true,false,false,"food");
            myDataBase.insertData("Shellac","insect secretion",false,true,false,"food");
            myDataBase.insertData("Silk","cloth made from the fiber produced by the larvae ('silk worm') of certain bombycine moths, the harvesting of which entails the destruction of the insect.",false,true,false,"food");
            myDataBase.insertData("Sodium Alginate","the sodium salt of alginic acid extracted from brown seaweed.",true,false,false,"food");
            myDataBase.insertData("Sodium Aluminum Sulfate","white solid used as an acidity regulator in foods. It is mainly used in the manufacturing of baking powder. Produced by combining sodium sulfate and aluminum sulfate.",true,false,false,"food");
            myDataBase.insertData("Sodium Benzoate"," the sodium salt of benzoic acid. Produced by reacting sodium hydroxide with benzoic acid. Used as a food preservative.",true,false,false,"food");
            myDataBase.insertData("Sodium Bicarbonate/Baking Soda","bicarbonate of Soda",true,false,false,"food");
            myDataBase.insertData("Sodium Carbonate","Soda Ash. A sodium salt of carbonic acid.",true,false,false,"food");
            myDataBase.insertData("Sodium Chloride","chemical term for table salt. It can be mined (rock salt), obtained by adding water to salt deposits (evaporated salt) or obtained from oceans and salt lakes (sea salt).",true,false,false,"food");
            myDataBase.insertData("Sodium Hydroxide","Caustic Soda. A water-soluble solid usually produced by processing salt water. It used to be obtained from the ashes of a certain kind of seaweed.",true,false,false,"food");
            myDataBase.insertData("Sodium Lactate","the sodium salt of lactic acid.",false,false,true,"food");
            myDataBase.insertData("Sodium Laureth Sulfate (SLES)","the sodium salt of sulfated ethoxylated lauryl alcohol.",true,false,false,"food");
            myDataBase.insertData("Sodium Lauryl Sulfate (SLS)","prepared by sulfation of lauryl alcohol followed by neutralization with sodium carbonate",true,false,false,"food");
            myDataBase.insertData("Sodium Metabisulfite","an inorganic salt. White to yellowish powder with sulfur dioxide odor. Used as a disinfectant, antioxidant and preservative.",true,false,false,"food");
            myDataBase.insertData("Sodium Phosphate (mono-, di-, and tri-)","synthetic material generally prepared by the partial or total neutralization of phosphoric acid using sodium carbonate or sodium hydroxide.",true,false,false,"food");
            myDataBase.insertData("Sodium stearoyl-2-lactylate","prepared from lactic acid and fatty acids.",false,false,true,"food");
            myDataBase.insertData("Sodium Sulfate","the sodium salt of sulfuric acid.",true,false,false,"food");
            myDataBase.insertData("Sorbic acid","white powder obtained from fruit or produced synthetically.",true,false,false,"food");
            myDataBase.insertData("Sorbitan monolaurate","derived from raw materials of vegetable origin. Commercially known as Span 20",true,false,false,"food");
            myDataBase.insertData("Sorbitan monostearate","manufactured by reacting stearic acid with sorbitol to yield a mixture of esters. Commercially known as Span 60",false,false,true,"food");
            myDataBase.insertData("Sorbitan monooleate","derived from animal or vegetable sources. Commercially know as Span 80. The vegetable derived version is know as Span 80V",false,false,true,"food");
            myDataBase.insertData("Sorbitan monopalmitate","derived from raw materials of vegetable origin. Commercially know as Span 40.",true,false,false,"food");
            myDataBase.insertData("Sorbitan tristearate","derived from animal or vegetable sources. Commercially known as Span 65. The vegetable derived version is know as Span 65V.",false,false,true,"food");
            myDataBase.insertData("Sorbitan Trioleate","derived from animal or vegetable sources. Commercially know as Span 85. The vegetable derived version is known as Span 85V",false,false,true,"food");
            myDataBase.insertData("Sorbitol","a sugar alcohol derived from fruit like cherries, plums, pears, apples or from corn, seaweed and algae.",true,false,false,"food");
            myDataBase.insertData("Sourdough starter","also known as \"starter culture\", \"sourdough culture\" or \"sour culture\". It is usually made with a mixture of flour and water inhabited by yeast and lactobacteria containing no animal ingredients. Sometimes yogurt is used in the starter. Bread made from a sourdough culture is called sourdough bread.",false,false,true,"food");
            myDataBase.insertData("Sperm oil","oil found in the head of the various species of whales",false,true,false,"food");
            myDataBase.insertData("Spermaceti","fatty substance derived as a wax from the head of the sperm whale.",false,true,false,"food");
            myDataBase.insertData("Sponge","aquatic animal or colony of animals of a 'low order', characterized by a tough elastic skeleton of interlaced fibers.",false,false,true,"food");
            myDataBase.insertData("Sponge Iron","also known as Direct Reduced Iron (DRI) or Reduced Iron. It's used to fortify foods, like flour. It's produced from either iron ore or mill scale (the surface of hot rolled steel) by reduction with hydrogen or carbon monoxide.",true,false,false,"food");
            myDataBase.insertData("Squalene/squalane","found in the liver of the shark (and rats).",false,false,true,"food");
            myDataBase.insertData("Starch","a complex carbohydrate found in seeds, fruits, tubers, roots and stem pith of plants such as corn, potatoes, wheat, beans and rice.",true,false,false,"food");
            myDataBase.insertData("Stearate","salt of stearic acid.",false,false,true,"food");
            myDataBase.insertData("Stearic acid","fat from cows, pigs, sheep, dogs or cats. Can be obtained from vegetable sources.",false,false,true,"food");
            myDataBase.insertData("Stearin(e)","general name for the three glycerids (monostearin, distearin, tristearin). Formed by the combination of stearic acid and glycerin, chiefly applied to tristearin, which is the main constituent of tallow or suet.",false,false,true,"food");
            myDataBase.insertData("Stearyl alcohol","prepared from sperm whale oil or vegetable sources.",false,false,true,"food");
            myDataBase.insertData("Stearyl tartrate (Stearyl palmityl tartrate)","made from stearyl alcohol and tartaric acid",false,false,true,"food");
            myDataBase.insertData("Sucroglycerides","obtained by reacting sucrose with an edible fat or oil with or without the presence of a solvent.",false,false,true,"food");
            myDataBase.insertData("Sucralose","known under the brand name Splenda. It's produced from cane sugar. Some but not all Splenda producers have confirmed that they don't use bone char as a filter. Sucralose is tested on animals.",false,false,true,"food");
            myDataBase.insertData("Sucrose","a sweet crystallizable material that consists wholly or essentially of sucrose. It is obtained commercially from sugarcane or sugar beet. Beet sugar is vegan, but some cane sugars are processed through boneblack.",false,false,true,"food");
            myDataBase.insertData("Suede","kid-, pig- or calf-skin tanned.",false,false,true,"food");
            myDataBase.insertData("Suet","solid fat prepared from the kidneys of cattle and sheep.",false,false,true,"food");
            myDataBase.insertData("Sugar","a sweet crystallizable material that consists wholly or essentially of sucrose. It is obtained commercially from sugarcane or sugar beet. Beet sugar is vegan, but some cane sugars are processed through boneblack.",false,false,true,"food");
            myDataBase.insertData("Sulfur Dioxide","a toxic colorless gas formed primarily by the combustion of sulfur-containing material, like fossil fuels.",true,false,false,"food");
            myDataBase.insertData("Sulfuric Acid","oil of vitriol. A highly corrosive acid usually produced from sulfur dioxide.",true,false,false,"food");
            myDataBase.insertData("Sunette","Potassium/Acesulfame K/Ace K (V): an artificial sweetener. Also sold commercially as Sunette or Sweet One. It has no nutritional value or calories. Might increase cancer risk in humans.",true,false,false,"food");
            myDataBase.insertData("Sunset Yellow FCF"," Derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("Synthetic","ingredient is produced by chemical synthesis, which means that parts or elements are combined to form a whole. Unlike artificial products, synthetic products are made from ingredients that do not occur (independently) in nature.",true,false,false,"food");
            myDataBase.insertData("Tallow","hard animal fat, especially that obtained from the parts about the kidneys of ruminating animals.",false,true,false,"food");
            myDataBase.insertData("Tartaric Acid","an organic acid present in many fruits, especially in grapes. Usually obtained as a byproduct of wine making.",true,false,false,"food");
            myDataBase.insertData("Tartrazine"," Derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("TBHQ (Tertiary Butylhydroquinone)","a synthetic food preservative used in oils, margarines, crackers, fast foods and many other food products. It's produced from phenol and butane.",true,false,false,"food");
            myDataBase.insertData("Tertiary Butylhydroquinone","a synthetic food preservative used in oils, margarines, crackers, fast foods and many other food products. It's produced from phenol and butane.",true,false,false,"food");
            myDataBase.insertData("Testosterone","male hormone.",false,false,true,"food");
            myDataBase.insertData("Thiamine Mononitrate","synthetic form of vitamin B1. It's synthesized by removing a chloride ion from thiamine hydrochloride and by mixing it with nitric acid.",true,false,false,"food");
            myDataBase.insertData("Thiamine Hydrocholide","synthetic form of vitamin B1. Produced from coal tar, ammonia, acetone and hydrochloric acid.",true,false,false,"food");
            myDataBase.insertData("Toluene","a colorless liquid hydrocarbon, derived from petroleum processing.",true,false,false,"food");
            myDataBase.insertData("Tricalcium Phosphate"," (Calcium phosphate, tribasic) The tribasic form of calcium phosphate. Also known as calcium orthophosphate. Consists of a mixture of calcium phosphates.",false,false,true,"food");
            myDataBase.insertData("Tocopherols","natural-source vitamin E (d-alpha-tocopherol) is obtained by distillation of vegetable oils (primarily from soya beans, rapeseed and sunflower); synthetic chemically manufactured vitamin E (dl-alpha-tocopherol)is a mixture of eight diastereoisomers in equal proportions.",true,false,false,"food");
            myDataBase.insertData("Trypsin","enzyme usually derived from porcine pancreas.",false,true,false,"food");
            myDataBase.insertData("Turmeric","an East Indian perennial herb.",true,false,false,"food");
            myDataBase.insertData("Urea","also known as carbamide. A waste product of digested protein filtered out by the kidneys and excreted from the body in urine. Commercially it is almost always produced from synthetic ammonia and carbon dioxide. It is rarely produced from animal urine.",false,false,true,"food");
            myDataBase.insertData("Vegetable Carbon","derived from either burnt vegetable matter, incomplete combustion of natural gas, activated charcoal, bones, blood, meat or various fats oils and resins.",false,false,true,"food");
            myDataBase.insertData("Vegetable Glycerin","Glycerin derived from vegetable fats.",true,false,false,"food");
            myDataBase.insertData("Vellum","fine parchment prepared from the skins of calves, lambs or kids.",false,false,true,"food");
            myDataBase.insertData("Vitamin A","an aliphatic alcohol. Some possible sources are fish liver oil, egg yolks, butter, lemongrass, carrots or synthetics.",false,false,true,"food");
            myDataBase.insertData("Vitamin B1 (Thiamine, Thiamin)","used to fortify foods or as a supplement. Two of the synthetic forms are known as thiamine mononitrate and thiamine hydrocholide.",false,false,true,"food");
            myDataBase.insertData("Vitamin B2 (Riboflavin, Lactoflavin)","used as a food coloring, to fortify foods or as a supplement. Produced synthetically or by a fermentation process with genetically modified Bacillus subtilis. It's usually vegan, but in rare cases it can be produced from animal sources like beef, especially when it's marked as being \"natural\".",false,false,true,"food");
            myDataBase.insertData("Vitamin B3 (Niacin, Nicotinic Acid)","used as a cholesterol treatment, to fortify foods or as a supplement. The largest commercial use is to fortify animal feed. It's usually produced synthetically. In rare cases it can be derived from animal sources.",false,false,true,"food");
            myDataBase.insertData("Vitamin B7","a whitish crystlalline powder, also known as Vitamin H, Vitamin B Factor or Vitamin B7. It can be derived from animal cells, milk or plant sources (like fruits) or produced synthetically.",false,false,true,"food");
            myDataBase.insertData("Vitamin B9 (Folic Acid, Folate)","used to fortify foods or as a supplement, especially for pregnant women. It's usually produced synthetically. Folate is the general term for Vitamin B9, whereas folic acid refers to the synthetic compound used in supplements and food fortification.",false,false,true,"food");
            myDataBase.insertData("Vitamin B Factor","a whitish crystlalline powder, also known as Vitamin H, Vitamin B Factor or Vitamin B7. It can be derived from animal cells, milk or plant sources (like fruits) or produced synthetically.",false,false,true,"food");
            myDataBase.insertData("Vitamin C"," a water-soluble vitamin found in vegetables and fruits or made synthetically.",true,false,false,"food");
            myDataBase.insertData("Vitamin D2 (Ergocalciferol)","vitamin usually derived from plant sterols or yeast. Can also be derived from animal fats.",false,false,true,"food");
            myDataBase.insertData("Vitamin D3 (Cholecalciferol)","vitamin usually derived from animal sources like lanolin, milk, egg yolk and fish liver oil. Can also be derived from microbial or synthetic sources. Please note that synthetic vitamin D3 can have an animal ingredient as their starting raw material.",false,false,true,"food");
            myDataBase.insertData("Vitamin E","natural-source vitamin E (d-alpha-tocopherol) is obtained by distillation of vegetable oils (primarily from soya beans, rapeseed and sunflower); synthetic chemically manufactured vitamin E (dl-alpha-tocopherol)is a mixture of eight diastereoisomers in equal proportions.",true,false,false,"food");
            myDataBase.insertData("Vitamin H","a whitish crystlalline powder, also known as Vitamin H, Vitamin B Factor or Vitamin B7. It can be derived from animal cells, milk or plant sources (like fruits) or produced synthetically.",false,false,true,"food");
            myDataBase.insertData("Volaise","ostrich meat.",false,true,false,"food");
            myDataBase.insertData("Waxy maze","Corn starch. The sticky material from the inside of the corn kernel.",true,false,false,"food");
            myDataBase.insertData("Waxed Paper","is often coated with paraffin or tallow . Waxed paper from the companies If You Care (100% soybean wax) and Natural Value (100% paraffin) are vegan.",false,false,true,"food");
            myDataBase.insertData("Whey","residue from milk after the removal of the casein and most of the fat. By-product of cheese making.",false,true,false,"food");
            myDataBase.insertData("White Mineral Oil"," obtained from petroleum and used in baked goods.",true,false,false,"food");
            myDataBase.insertData("Wool","hair forming the fleecy coat of the domesticated sheep (and similar animals).",false,true,false,"food");
            myDataBase.insertData("Xanthan gum (corn sugar gum)","gum produced by the fermentation of corn sugar with a microbe called Xanthomonas campestris.",true,false,false,"food");
            myDataBase.insertData("Yeast","a microscopic unicellular fungus. Different yeast products include baker's yeast, nutritional yeast and brewer's yeast.",true,false,false,"food");
            myDataBase.insertData("Yellow 5","Tartrazine . Derived from coal tar. See Colors/Dyes.",false,false,true,"food");
            myDataBase.insertData("Yellow 6","Monoazo. Derived from coal tar. See Colors/Dyes.",false,false,true,"food");

            myDataBase.insertData("Product", "Test product", false, false, true, "product");
        }
    }
}
