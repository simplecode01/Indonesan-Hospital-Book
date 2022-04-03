@AndroidEntryPoint
class ListKotaKecamatanFragment : Fragment(R.layout.fragment_list_provinsi) {
    private val binding: FragmentListProvinsinBinding by viewBinding()
    private val viewModelSave: MainViewModel by activityViewModels()
    private val viewModelMainHilt: MainHiltViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      getListProv()
    }
    private fun getListProv(){
      viewModelMainHilt.getProvince().observe(viewLifecycleOwner){resource ->
      	when(resource.status){
        	Status.LOADING ->{
            	binding.pvLoading.isVisible = true
                binding.tvWaitLoading.isVisible = true
            }
            Status.SUCCESS ->{
              	binding.pvLoading.isVisible = false
                binding.tvWaitLoading.isVisible = false
              	resource.data?.let { dataProv ->
                  val listProvince = dataProv.provinces
                  val adapter = ListProvinceAdapter(listProvince)
                  binding.rvProvince.adapter = adapter
                  adapter.clicklistener ={ prov ->
                    viewModelSave.provID = prov.id
                    findNavController().navigate(R.id.action_nav_list_prov_to_nav_list_kota)
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