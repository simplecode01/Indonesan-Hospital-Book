class SaveInputFragment : Fragment(R.layout.fragment_save_input) {
    private val binding: FragmentSaveInputBinding by viewBinding()
    private val viewModelSave: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val provID = viewModelSave.provinceId
        val provName = viewModelSave.provName
        val cityID = viewModelSave.cityId
        val cityName = viewModelSave.cityName
        binding.tvProv.text = provName
        binding.tvCity.text = cityName
        binding.btnSave.setOnClickListener {
            SaveSharedPrefrences(requireContext()).provinsisaveid = provID
            SaveSharedPrefrences(requireContext()).provinsisave = provName
            SaveSharedPrefrences(requireContext()).kotasaveid = cityID
            SaveSharedPrefrences(requireContext()).kotasave = cityName
            Toast.makeText(requireContext(), "Succesfully Save Your Location", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_nav_input_save_to_nav_home)
        }
    }
}
