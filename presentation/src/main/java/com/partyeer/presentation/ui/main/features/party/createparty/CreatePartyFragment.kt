package com.partyeer.presentation.ui.main.features.party.createparty


class CreatePartyFragment {

    /* private lateinit var imageUri : Uri

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         setHasOptionsMenu(true)
     }

     override fun initViews() {
         with(binding.imageViewPartyLogo){
             setOnClickListener {
                 viewModel.selectImage(requireActivity())
             }
         }
     }

     override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         inflater.inflate(R.menu.menu_create_party, menu)
     }

     override fun observeEvents() {
         super.observeEvents()
         //Collect Party
         viewLifecycleOwner.lifecycleScope.launch {
             viewModel.party.collect {
                 with(binding) {
                     textViewPartyTitle.editText?.setText(it.id)
                 }
             }
         }
     }

     override fun onOptionsItemSelected(item: MenuItem) =
         when (item.itemId) {
             R.id.action_publish -> {
                 val party = Party(
                     binding.textViewPartyTitle.editText?.text.toString(),
                     "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
                     binding.textViewPartyTitle.editText?.text.toString(),
                     binding.textViewPartyConcept.editText?.text.toString(),
                     0.0
                 )
                 viewModel.createParty(party)
                 true
             }
             else -> super.onOptionsItemSelected(item)
         }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

         if(requestCode == 100 && resultCode == AppCompatActivity.RESULT_OK){
             imageUri = data?.data!!
             binding.imageViewPartyLogo.setImageURI(imageUri)
         }
     }
 */
}