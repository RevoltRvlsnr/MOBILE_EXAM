package com.exam.prayoga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var stationAdapter: StationAdapter
    private var dataStations: List<Station> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val call = ApiService.apiService.getStations()
        call.enqueue(object : Callback<List<Station>> {
            override fun onResponse(call: Call<List<Station>>, response: Response<List<Station>>) {
                val stationList = response.body()
                stationList?.let {
                    val a = removeDuplicateStations(it)
                    val b = updateStationsWithDescriptionAndImage(a)
                    dataStations = b
                    stationAdapter = StationAdapter(b)
                    recyclerView.adapter = stationAdapter
                }
            }

            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                // Handle error
            }
        })

        setupSearchView()
    }

    private fun setupSearchView() {
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { query ->
                    val filteredStations = if (query.isEmpty()) {
                        dataStations
                    } else {
                        searchStationsByName(dataStations, query)
                    }
                    stationAdapter.searchList(filteredStations)
                }
                return true
            }
        })
    }

    private fun searchStationsByName(stations: List<Station>, query: String): List<Station> {
        val filteredStations = ArrayList<Station>()

        for (station in stations) {
            if (station.name.contains(query, ignoreCase = true)) {
                filteredStations.add(station)
            }
        }

        return filteredStations
    }

    fun removeDuplicateStations(stationList: List<Station>): List<Station> {
        val uniqueStations = mutableSetOf<String>() // Gunakan set untuk menyimpan nilai unik
        val filteredList = mutableListOf<Station>()

        for (station in stationList) {
            if (station.code !in uniqueStations) { // Cek jika code belum ada dalam set
                uniqueStations.add(station.code) // Tambahkan code ke set
                filteredList.add(station) // Tambahkan stasiun ke daftar hasil
            }
        }

        return filteredList
    }

    private fun updateStationsWithDescriptionAndImage(stationList: List<Station>): List<Station> {
        return stationList.map { station ->
            when (station.code) {
                "TNK" -> {
                    station.copy(
                        description = "TANJUNG KARANG adalah stasiun di Kota KOTA BANDAR LAMPUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LAR" -> {
                    station.copy(
                        description = "LABUAN RATU adalah stasiun di Kota KOTA BANDAR LAMPUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "RJS" -> {
                    station.copy(
                        description = "REJOSARI adalah stasiun di Kota KABUPATEN LAMPUNG SELATAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TGI" -> {
                    station.copy(
                        description = "TEGINENENG adalah stasiun di Kota KABUPATEN LAMPUNG UTARA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BKI" -> {
                    station.copy(
                        description = "BEKRI adalah stasiun di Kota KABUPATEN LAMPUNG TENGAH.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "HJP" -> {
                    station.copy(
                        description = "HAJI PEMANGGILAN adalah stasiun di Kota KABUPATEN LAMPUNG TENGAH.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SLS" -> {
                    station.copy(
                        description = "SULUSUBAN adalah stasiun di Kota KABUPATEN LAMPUNG TENGAH.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KB" -> {
                    station.copy(
                        description = "KOTABUMI adalah stasiun di Kota KOTABUMI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CEP" -> {
                    station.copy(
                        description = "CEMPAKA adalah stasiun di Kota KABUPATEN LAMPUNG UTARA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KTP" -> {
                    station.copy(
                        description = "KETAPANG adalah stasiun di Kota KABUPATEN LAMPUNG UTARA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "NRR" -> {
                    station.copy(
                        description = "NEGARA RATU adalah stasiun di Kota KABUPATEN LAMPUNG UTARA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TLY" -> {
                    station.copy(
                        description = "TULUNG BUYUT adalah stasiun di Kota KABUPATEN LAMPUNG UTARA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "NGN" -> {
                    station.copy(
                        description = "NEGERIAGUNG adalah stasiun di Kota KABUPATEN WAY KANAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BBU" -> {
                    station.copy(
                        description = "BLAMBANGAN UMPU adalah stasiun di Kota KABUPATEN WAY KANAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GHM" -> {
                    station.copy(
                        description = "GIHAM adalah stasiun di Kota KABUPATEN WAY KANAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WAY" -> {
                    station.copy(
                        description = "WAYTUBA adalah stasiun di Kota KABUPATEN WAY KANAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MP" -> {
                    station.copy(
                        description = "MARTAPURA adalah stasiun di Kota MARTAPURA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PNW" -> {
                    station.copy(
                        description = "PENINJAWAN adalah stasiun di Kota PENINJAWAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TI" -> {
                    station.copy(
                        description = "TEBING TINGGI adalah stasiun di Kota KOTA TEBING TINGGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LT" -> {
                    station.copy(
                        description = "LAHAT adalah stasiun di Kota KABUPATEN LAHAT.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SCT" -> {
                    station.copy(
                        description = "SUKACINTA adalah stasiun di Kota KABUPATEN LAHAT.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "ME" -> {
                    station.copy(
                        description = "MUARA ENIM adalah stasiun di Kota KABUPATEN MUARA ENIM.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PBM" -> {
                    station.copy(
                        description = "PRABUMULIH adalah stasiun di Kota KOTA PRABUMULIH.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GB" -> {
                    station.copy(
                        description = "GOMBONG adalah stasiun di Kota KABUPATEN KEBUMEN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KPT" -> {
                    station.copy(
                        description = "KERTAPATI adalah stasiun di Kota KOTA PALEMBANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LLG" -> {
                    station.copy(
                        description = "LUBUK LINGGAU adalah stasiun di Kota KOTA LUBUKLINGGAU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BTA" -> {
                    station.copy(
                        description = "BATURAJA adalah stasiun di Kota BATURAJA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GMR" -> {
                    station.copy(
                        description = "GAMBIR adalah stasiun di Kota JAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JUA" -> {
                    station.copy(
                        description = "JUANDA adalah stasiun di Kota KOTA JAKARTA PUSAT.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JAKK" -> {
                    station.copy(
                        description = "JAKARTA KOTA adalah stasiun di Kota JAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TGS" -> {
                    station.copy(
                        description = "TIGARAKSA adalah stasiun di Kota KABUPATEN TANGERANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CLG" -> {
                    station.copy(
                        description = "CILEGON adalah stasiun di Kota KOTA CILEGON.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KNN" -> {
                    station.copy(
                        description = "KRADENAN adalah stasiun di Kota KABUPATEN GROBOGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JBN" -> {
                    station.copy(
                        description = "JAMBON adalah stasiun di Kota KABUPATEN GROBOGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LW" -> {
                    station.copy(
                        description = "LAWANG adalah stasiun di Kota KOTA MALANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "ML" -> {
                    station.copy(
                        description = "MALANG adalah stasiun di Kota KOTA MALANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MLK" -> {
                    station.copy(
                        description = "MALANG KOTA LAMA adalah stasiun di Kota KOTA MALANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KPN" -> {
                    station.copy(
                        description = "KEPANJEN adalah stasiun di Kota KOTA MALANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SBP" -> {
                    station.copy(
                        description = "SUMBERPUCUNG adalah stasiun di Kota KOTA MALANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KSB" -> {
                    station.copy(
                        description = "KESAMBEN adalah stasiun di Kota KOTA BLITAR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WG" -> {
                    station.copy(
                        description = "WLINGI adalah stasiun di Kota KOTA BLITAR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BL" -> {
                    station.copy(
                        description = "BLITAR adalah stasiun di Kota KOTA BLITAR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "NT" -> {
                    station.copy(
                        description = "NGUNUT adalah stasiun di Kota KABUPATEN TULUNGAGUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TA" -> {
                    station.copy(
                        description = "TULUNGAGUNG adalah stasiun di Kota KABUPATEN TULUNGAGUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "NJG" -> {
                    station.copy(
                        description = "NGUJANG adalah stasiun di Kota KABUPATEN TULUNGAGUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KD" -> {
                    station.copy(
                        description = "KEDIRI adalah stasiun di Kota KOTA KEDIRI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SI" -> {
                    station.copy(
                        description = "SUKABUMI adalah stasiun di Kota KOTA SUKABUMI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PSE" -> {
                    station.copy(
                        description = "PASARSENEN adalah stasiun di Kota JAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JNG" -> {
                    station.copy(
                        description = "JATINEGARA adalah stasiun di Kota JAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GM" -> {
                    station.copy(
                        description = "GUMILIR adalah stasiun di Kota KABUPATEN CILACAP.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CP" -> {
                    station.copy(
                        description = "CILACAP adalah stasiun di Kota KABUPATEN CILACAP.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "DMR" -> {
                    station.copy(
                        description = "DOLOKMERANGIR adalah stasiun di Kota KABUPATEN SIMALUNGUN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LTD" -> {
                    station.copy(
                        description = "LAUT TADOR adalah stasiun di Kota KABUPATEN BATU BARA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PRA" -> {
                    station.copy(
                        description = "PERLANAAN adalah stasiun di Kota KABUPATEN SIMALUNGUN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LMP" -> {
                    station.copy(
                        description = "LIMAPULUH adalah stasiun di Kota KABUPATEN SIMALUNGUN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SBJ" -> {
                    station.copy(
                        description = "SEI BEJANGKAR adalah stasiun di Kota KABUPATEN LABUHAN BATU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KIS" -> {
                    station.copy(
                        description = "KISARAN adalah stasiun di Kota KABUPATEN ASAHAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PUR" -> {
                    station.copy(
                        description = "PULURAJA adalah stasiun di Kota KABUPATEN ASAHAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "AKB" -> {
                    station.copy(
                        description = "AEKLOBA adalah stasiun di Kota KABUPATEN ASAHAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MBM" -> {
                    station.copy(
                        description = "MAMBANGMUDA adalah stasiun di Kota KABUPATEN LABUHAN BATU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PME" -> {
                    station.copy(
                        description = "PAMINGKE adalah stasiun di Kota KABUPATEN LABUHANBATU UTARA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PHA" -> {
                    station.copy(
                        description = "PADANGHALABAN adalah stasiun di Kota KABUPATEN LABUHAN BATU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MBU" -> {
                    station.copy(
                        description = "MARBAU adalah stasiun di Kota KABUPATEN LABUHAN BATU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TNB" -> {
                    station.copy(
                        description = "TANJUNGBALAI adalah stasiun di Kota KOTA TANJUNG BALAI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TBI" -> {
                    station.copy(
                        description = "TEBING TINGGI adalah stasiun di Kota KOTA TEBING TINGGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "RPH" -> {
                    station.copy(
                        description = "RAMPAH adalah stasiun di Kota KOTA TEBING TINGGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PBA" -> {
                    station.copy(
                        description = "PERBAUNGAN adalah stasiun di Kota KABUPATEN SERDANG BEDAGAI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LBP" -> {
                    station.copy(
                        description = "LUBUKPAKAM adalah stasiun di Kota KABUPATEN DELI SERDANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "ARB" -> {
                    station.copy(
                        description = "ARASKABU adalah stasiun di Kota KABUPATEN DELI SERDANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BTK" -> {
                    station.copy(
                        description = "BATANGKUIS adalah stasiun di Kota KABUPATEN DELI SERDANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BAP" -> {
                    station.copy(
                        description = "BANDARHALIPAH adalah stasiun di Kota KABUPATEN DELI SERDANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MDN" -> {
                    station.copy(
                        description = "MEDAN adalah stasiun di Kota KOTA MEDAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "RAP" -> {
                    station.copy(
                        description = "RANTAUPRAPAT adalah stasiun di Kota KABUPATEN LABUHAN BATU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SIR" -> {
                    station.copy(
                        description = "SIANTAR adalah stasiun di Kota KOTA PEMATANG SIANTAR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KAC" -> {
                    station.copy(
                        description = "KIARACONDONG adalah stasiun di Kota KOTA BANDUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BD" -> {
                    station.copy(
                        description = "BANDUNG adalah stasiun di Kota KOTA BANDUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CMI" -> {
                    station.copy(
                        description = "CIMAHI adalah stasiun di Kota KOTA CIMAHI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PLD" -> {
                    station.copy(
                        description = "PLERED adalah stasiun di Kota KABUPATEN PURWAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PWK" -> {
                    station.copy(
                        description = "PURWAKARTA adalah stasiun di Kota KABUPATEN PURWAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CKP" -> {
                    station.copy(
                        description = "CIKAMPEK adalah stasiun di Kota KABUPATEN KARAWANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KW" -> {
                    station.copy(
                        description = "KARAWANG adalah stasiun di Kota KABUPATEN KARAWANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CKR" -> {
                    station.copy(
                        description = "CIKARANG adalah stasiun di Kota KABUPATEN BEKASI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BKS" -> {
                    station.copy(
                        description = "BEKASI adalah stasiun di Kota KOTA BEKASI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BJR" -> {
                    station.copy(
                        description = "BANJAR adalah stasiun di Kota KOTA BANJAR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CI" -> {
                    station.copy(
                        description = "CIAMIS adalah stasiun di Kota KABUPATEN CIAMIS.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TSM" -> {
                    station.copy(
                        description = "TASIKMALAYA adalah stasiun di Kota KOTA TASIKMALAYA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CPD" -> {
                    station.copy(
                        description = "CIPEUNDEUY adalah stasiun di Kota KABUPATEN GARUT.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CB" -> {
                    station.copy(
                        description = "CIBATU adalah stasiun di Kota KABUPATEN GARUT.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LL" -> {
                    station.copy(
                        description = "LELES adalah stasiun di Kota KABUPATEN GARUT.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CCL" -> {
                    station.copy(
                        description = "CICALENGKA adalah stasiun di Kota KABUPATEN BANDUNG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KTA" -> {
                    station.copy(
                        description = "KUTOARJO adalah stasiun di Kota KABUPATEN PURWOREJO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JN" -> {
                    station.copy(
                        description = "JENAR adalah stasiun di Kota KABUPATEN PURWOREJO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WJ" -> {
                    station.copy(
                        description = "WOJO adalah stasiun di Kota KABUPATEN PURWOREJO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KDG" -> {
                    station.copy(
                        description = "KEDUNDANG adalah stasiun di Kota KABUPATEN KULON PROGO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WT" -> {
                    station.copy(
                        description = "WATES adalah stasiun di Kota KOTA YOGYAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "YK" -> {
                    station.copy(
                        description = "YOGYAKARTA adalah stasiun di Kota KOTA YOGYAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LPN" -> {
                    station.copy(
                        description = "LEMPUYANGAN adalah stasiun di Kota KOTA YOGYAKARTA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KT" -> {
                    station.copy(
                        description = "KLATEN adalah stasiun di Kota KABUPATEN KLATEN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PWS" -> {
                    station.copy(
                        description = "PURWOSARI adalah stasiun di Kota KOTA SOLO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SLO" -> {
                    station.copy(
                        description = "SOLO BALAPAN adalah stasiun di Kota KOTA SOLO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SLM" -> {
                    station.copy(
                        description = "SALEM adalah stasiun di Kota KABUPATEN SRAGEN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GD" -> {
                    station.copy(
                        description = "GUNDIH adalah stasiun di Kota KABUPATEN GROBOGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TW" -> {
                    station.copy(
                        description = "TELAWA adalah stasiun di Kota KABUPATEN BOYOLALI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KEJ" -> {
                    station.copy(
                        description = "KEDUNGJATI adalah stasiun di Kota KABUPATEN GROBOGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SMT" -> {
                    station.copy(
                        description = "SEMARANG TAWANG BANK JATENG adalah stasiun di Kota KOTA SEMARANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SMC" -> {
                    station.copy(
                        description = "SEMARANG PONCOL adalah stasiun di Kota KOTA SEMARANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WLR" -> {
                    station.copy(
                        description = "WELERI adalah stasiun di Kota KABUPATEN KENDAL.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BTG" -> {
                    station.copy(
                        description = "BATANG adalah stasiun di Kota KOTA PEKALONGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PK" -> {
                    station.copy(
                        description = "PEKALONGAN adalah stasiun di Kota KOTA PEKALONGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PML" -> {
                    station.copy(
                        description = "PEMALANG adalah stasiun di Kota KOTA PEMALANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SLW" -> {
                    station.copy(
                        description = "SLAWI adalah stasiun di Kota KOTA TEGAL.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TG" -> {
                    station.copy(
                        description = "TEGAL adalah stasiun di Kota KOTA TEGAL.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BB" -> {
                    station.copy(
                        description = "BREBES adalah stasiun di Kota KABUPATEN BREBES.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TGN" -> {
                    station.copy(
                        description = "TANJUNG adalah stasiun di Kota KABUPATEN BREBES.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LOS" -> {
                    station.copy(
                        description = "LOSARI adalah stasiun di Kota KABUPATEN CIREBON.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BBK" -> {
                    station.copy(
                        description = "BABAKAN adalah stasiun di Kota KABUPATEN CIREBON.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CLD" -> {
                    station.copy(
                        description = "CILEDUG adalah stasiun di Kota KABUPATEN CIREBON.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PGB" -> {
                    station.copy(
                        description = "PEGADENBARU adalah stasiun di Kota KABUPATEN SUBANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "HGL" -> {
                    station.copy(
                        description = "HAURGEULIS adalah stasiun di Kota KABUPATEN INDRAMAYU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TIS" -> {
                    station.copy(
                        description = "TERISI adalah stasiun di Kota KABUPATEN INDRAMAYU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JTB" -> {
                    station.copy(
                        description = "JATIBARANG adalah stasiun di Kota KABUPATEN INDRAMAYU.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "AWN" -> {
                    station.copy(
                        description = "ARJAWINANGUN adalah stasiun di Kota KABUPATEN CIREBON.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CN" -> {
                    station.copy(
                        description = "CIREBON adalah stasiun di Kota KOTA CIREBON.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CNP" -> {
                    station.copy(
                        description = "CIREBON PRUJAKAN adalah stasiun di Kota KOTA CIREBON.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KGG" -> {
                    station.copy(
                        description = "KETANGGUNGAN adalah stasiun di Kota KABUPATEN BREBES.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PPK" -> {
                    station.copy(
                        description = "PRUPUK adalah stasiun di Kota KOTA TEGAL.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BMA" -> {
                    station.copy(
                        description = "BUMIAYU adalah stasiun di Kota KABUPATEN BREBES.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PWT" -> {
                    station.copy(
                        description = "PURWOKERTO adalah stasiun di Kota PURWOKERTO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MA" -> {
                    station.copy(
                        description = "MAOS adalah stasiun di Kota KABUPATEN CILACAP.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JRL" -> {
                    station.copy(
                        description = "JERUKLEGI adalah stasiun di Kota KABUPATEN CILACAP.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SDR" -> {
                    station.copy(
                        description = "SIDAREJA adalah stasiun di Kota KABUPATEN CILACAP.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GDM" -> {
                    station.copy(
                        description = "GANDRUNGMANGUN adalah stasiun di Kota KABUPATEN CILACAP.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CSA" -> {
                    station.copy(
                        description = "CISAAT adalah stasiun di Kota KOTA SUKABUMI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KE" -> {
                    station.copy(
                        description = "KARANGTENGAH adalah stasiun di Kota KOTA SUKABUMI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CBD" -> {
                    station.copy(
                        description = "CIBADAK adalah stasiun di Kota KOTA SUKABUMI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PRK" -> {
                    station.copy(
                        description = "PARUNG KUDA adalah stasiun di Kota KOTA SUKABUMI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CCR" -> {
                    station.copy(
                        description = "CICURUG adalah stasiun di Kota KOTA SUKABUMI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CGB" -> {
                    station.copy(
                        description = "CIGOMBONG adalah stasiun di Kota KABUPATEN BOGOR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MSG" -> {
                    station.copy(
                        description = "MASENG adalah stasiun di Kota KABUPATEN BOGOR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BTT" -> {
                    station.copy(
                        description = "BATUTULIS adalah stasiun di Kota KABUPATEN BOGOR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BOO" -> {
                    station.copy(
                        description = "BOGOR adalah stasiun di Kota KOTA BOGOR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KYA" -> {
                    station.copy(
                        description = "KROYA adalah stasiun di Kota KABUPATEN CILACAP.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SPH" -> {
                    station.copy(
                        description = "SUMPIUH adalah stasiun di Kota KABUPATEN BANYUMAS.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KA" -> {
                    station.copy(
                        description = "KARANGANYAR adalah stasiun di Kota KABUPATEN KEBUMEN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KM" -> {
                    station.copy(
                        description = "KEBUMEN adalah stasiun di Kota KABUPATEN KEBUMEN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KWN" -> {
                    station.copy(
                        description = "KUTOWINANGUN adalah stasiun di Kota KABUPATEN KEBUMEN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GBN" -> {
                    station.copy(
                        description = "GAMBRINGAN adalah stasiun di Kota KABUPATEN GROBOGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "NBO" -> {
                    station.copy(
                        description = "NGROMBO adalah stasiun di Kota KABUPATEN GROBOGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WR" -> {
                    station.copy(
                        description = "WARU adalah stasiun di Kota SIDOARJO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KRN" -> {
                    station.copy(
                        description = "KRIAN adalah stasiun di Kota SIDOARJO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MR" -> {
                    station.copy(
                        description = "MOJOKERTO adalah stasiun di Kota KOTA MOJOKERTO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CRM" -> {
                    station.copy(
                        description = "CURAHMALANG adalah stasiun di Kota KABUPATEN JOMBANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JG" -> {
                    station.copy(
                        description = "JOMBANG adalah stasiun di Kota KABUPATEN JOMBANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SMB" -> {
                    station.copy(
                        description = "SEMBUNG adalah stasiun di Kota KABUPATEN JOMBANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KTS" -> {
                    station.copy(
                        description = "KERTOSONO adalah stasiun di Kota KABUPATEN NGANJUK.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "NJ" -> {
                    station.copy(
                        description = "NGANJUK adalah stasiun di Kota KABUPATEN NGANJUK.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CRB" -> {
                    station.copy(
                        description = "CARUBAN adalah stasiun di Kota KOTA MADIUN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MN" -> {
                    station.copy(
                        description = "MADIUN adalah stasiun di Kota KOTA MADIUN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GG" -> {
                    station.copy(
                        description = "GENENG adalah stasiun di Kota KABUPATEN NGAWI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PA" -> {
                    station.copy(
                        description = "PARON adalah stasiun di Kota KABUPATEN NGAWI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KG" -> {
                    station.copy(
                        description = "KEDUNGGALAR adalah stasiun di Kota KABUPATEN NGAWI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WK" -> {
                    station.copy(
                        description = "WALIKUKUN adalah stasiun di Kota KABUPATEN NGAWI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SR" -> {
                    station.copy(
                        description = "SRAGEN adalah stasiun di Kota KABUPATEN SRAGEN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SK" -> {
                    station.copy(
                        description = "SOLOJEBRES adalah stasiun di Kota KOTA SOLO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "RGP" -> {
                    station.copy(
                        description = "ROGOJAMPI adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TGR" -> {
                    station.copy(
                        description = "TEMUGURUH adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KSL" -> {
                    station.copy(
                        description = "KALISETAIL adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SWD" -> {
                    station.copy(
                        description = "SUMBER WADUNG adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GLM" -> {
                    station.copy(
                        description = "GLENMORE adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KBR" -> {
                    station.copy(
                        description = "KALIBARU adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KLT" -> {
                    station.copy(
                        description = "KALISAT adalah stasiun di Kota KABUPATEN JEMBER.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "AJ" -> {
                    station.copy(
                        description = "ARJASA adalah stasiun di Kota KABUPATEN JEMBER.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JR" -> {
                    station.copy(
                        description = "JEMBER adalah stasiun di Kota KABUPATEN JEMBER.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "RBP" -> {
                    station.copy(
                        description = "RAMBIPUJI adalah stasiun di Kota KABUPATEN JEMBER.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "TGL" -> {
                    station.copy(
                        description = "TANGGUL adalah stasiun di Kota KABUPATEN JEMBER.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "JTR" -> {
                    station.copy(
                        description = "JATIROTO adalah stasiun di Kota KABUPATEN LUMAJANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KK" -> {
                    station.copy(
                        description = "KLAKAH adalah stasiun di Kota KABUPATEN LUMAJANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "RN" -> {
                    station.copy(
                        description = "RANUYOSO adalah stasiun di Kota KABUPATEN LUMAJANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PB" -> {
                    station.copy(
                        description = "PROBOLINGGO adalah stasiun di Kota KABUPATEN PROBOLINGGO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PS" -> {
                    station.copy(
                        description = "PASURUAN adalah stasiun di Kota KOTA PASURUAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BG" -> {
                    station.copy(
                        description = "BANGIL adalah stasiun di Kota KOTA PASURUAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SDA" -> {
                    station.copy(
                        description = "SIDOARJO adalah stasiun di Kota SIDOARJO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WO" -> {
                    station.copy(
                        description = "WONOKROMO adalah stasiun di Kota KOTA SURABAYA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SGU" -> {
                    station.copy(
                        description = "SURABAYA GUBENG adalah stasiun di Kota KOTA SURABAYA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SBI" -> {
                    station.copy(
                        description = "SURABAYA PASAR TURI adalah stasiun di Kota KOTA SURABAYA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LMG" -> {
                    station.copy(
                        description = "LAMONGAN adalah stasiun di Kota KABUPATEN LAMONGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BJ" -> {
                    station.copy(
                        description = "BOJONEGORO adalah stasiun di Kota KABUPATEN BOJONEGORO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "CU" -> {
                    station.copy(
                        description = "CEPU adalah stasiun di Kota KABUPATEN BLORA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WDU" -> {
                    station.copy(
                        description = "WADU adalah stasiun di Kota KABUPATEN BLORA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "RBG" -> {
                    station.copy(
                        description = "RANDUBLATUNG adalah stasiun di Kota KABUPATEN BLORA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "DPL" -> {
                    station.copy(
                        description = "DOPLANG adalah stasiun di Kota KABUPATEN BLORA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "MAG" -> {
                    station.copy(
                        description = "MAGETAN adalah stasiun di Kota MAGETAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "KTG" -> {
                    station.copy(
                        description = "KETAPANG adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BBT" -> {
                    station.copy(
                        description = "BABAT adalah stasiun di Kota KABUPATEN LAMONGAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "LEC" -> {
                    station.copy(
                        description = "LECES adalah stasiun di Kota KABUPATEN PROBOLINGGO.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "PDX" -> {
                    station.copy(
                        description = "PADANGX adalah stasiun di Kota KOTA PADANG.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BOP" -> {
                    station.copy(
                        description = "BOGOR PALEDANG adalah stasiun di Kota KOTA BOGOR.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "SGT" -> {
                    station.copy(
                        description = "SUNGAI TUHA adalah stasiun di Kota MARTAPURA.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "WAP" -> {
                    station.copy(
                        description = "WAY PISANG adalah stasiun di Kota KABUPATEN WAY KANAN.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "NGW" -> {
                    station.copy(
                        description = "NGAWI adalah stasiun di Kota KABUPATEN NGAWI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "BWI" -> {
                    station.copy(
                        description = "BANYUWANGI KOTA adalah stasiun di Kota KABUPATEN BANYUWANGI.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                "GRT" -> {
                    station.copy(
                        description = "GARUT adalah stasiun di Kota KABUPATEN GARUT.",
                        imageResId = R.drawable.ic_station_placeholder
                    )
                }
                else -> station
            }
        }
    }
}
