# PetDoc포트폴리오
+ HomeFragment

![GOMCAM 20210618_2008090219](https://user-images.githubusercontent.com/74043711/122726492-02072880-d2b1-11eb-9d59-91cd5c9c21f5.png)
```
class home_Frag() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as HomeActivity).topbar()
        return inflater.inflate(R.layout.fragment_home_, container, false)
    }

    override fun onStart() {
        super.onStart()

        login.setOnClickListener {
            activity?.let {
                val intent = Intent(context, InfoActivity::class.java)
                startActivity(intent)
            }
        }
        chat.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ChatActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
```
***

+ MapFragment

![GOMCAM 20210618_2008130467](https://user-images.githubusercontent.com/74043711/122726644-2a8f2280-d2b1-11eb-99ad-9516e643b7da.png)
```
class map_Frag : Fragment(),OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:View = inflater.inflate(R.layout.fragment_map_,container,false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // Inflate the layout for this fragment
        return rootView
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap =p0
        val marker = LatLng(37.50463,127.02248)
        mMap.addMarker(MarkerOptions().position(marker).title("서울특별시 서초구 강남대로79길 32 (반포동) 3층 사무실"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15F))
    }

}
```
***

+ ShopFragment

![GOMCAM 20210618_2008190516](https://user-images.githubusercontent.com/74043711/122726664-2d8a1300-d2b1-11eb-856a-0fd18ddc75ce.png)
```
class shop_Frag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_, container, false)
    }

    override fun onStart() {
        super.onStart()
        write.setOnClickListener { writego() }
        (activity as HomeActivity).read(context, readall(), recyclerView)

        swipe.setOnRefreshListener {
            (activity as HomeActivity).read(context, readall(), recyclerView)
            swipe.isRefreshing = false
        }
    }

    fun writego() {
//        (activity as HomeActivity).read(context,readall(),recyclerView)
        activity?.let {
            val intent = Intent(context, BoardWrite::class.java)
            startActivity(intent)
        }
    }

    fun readall(): ArrayList<BoardDto> {
        var listdata: ArrayList<BoardDto> = ArrayList<BoardDto>()
        var db: FirebaseFirestore = Firebase.firestore
        db.collection("board")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
//                    Log.d(""+document, "${document.id} => ${document.data}")
                    var a = document.data.toString()
                    var b = a.split(",", "{", "}", "[", "]")
                    Log.d("데이터확인", "" + b[1] + b[2] + b[3] + b[4] + b[5] + b[6] + b[7])
                    var anymal = b[1].split("=")
                    var uid = b[2].split("=")
                    var img = b[3].split("=")
                    var title = b[4].split("=")
                    var content = b[5].split("=")
                    var createDate = b[6].split("=")
                    var username = b[7].split("=")
                    var dto = BoardDto(
                        uid[1],
                        username[1],
                        anymal[1],
                        title[1],
                        content[1],
                        img[1],
                        createDate[1]
                    )
                    listdata.add(dto)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
        return listdata
    }
}
```
