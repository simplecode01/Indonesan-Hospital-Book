@AndroidEntryPoint
class ListKotaKecaSaveFragment : Fragment(R.layout.fragment_list_kota_keca_save) {
    private val binding: FragmentListKotaKecaSaveBinding by viewBinding()
    private val viewModelSave: MainViewModel by activityViewModels()
    private val viewModelMainHilt: MainHiltViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val provIdData = viewModelSave.provinceId
        getListKota(provIdData)
    }
    private fun getListKota(provinceId: String){
        viewModelMainHilt.getKotaKecamatan(provinceId).observe(viewLifecycleOwner){resource ->
            when(resource.status){
                Status.LOADING ->{
                    binding.pvLoading.isVisible = true
                    binding.tvWaitLoading.isVisible = true
                }
                Status.SUCCESS ->{
                    binding.pvLoading.isVisible = false
                    binding.tvWaitLoading.isVisible = false
                    resource.data?.let { datakota ->
                        val listKotaKecamatan = datakota.cities
                        val adapter = ListKotaKecamatanAdapter(listKotaKecamatan)
                        binding.rvKotaKecamatan.adapter = adapter
                        adapter.clickListenerr ={ kota ->
                            viewModelSave.cityId = kota.id
                            viewModelSave.cityName = kota.name
                            findNavController().navigate(R.id.action_nav_list_kota_keca_save_to_nav_input_save)
                        }
                    }
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), "ERROR, REASON : ${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
