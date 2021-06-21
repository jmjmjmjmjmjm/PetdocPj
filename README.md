# PetDoc포트폴리오

+ Home Fragment

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
