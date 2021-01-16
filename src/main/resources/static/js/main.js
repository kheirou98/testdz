/**
 * 
 */
$(document).ready(function(){

    $('.table .btn-primary').on('click',function(event){
    	event.preventDefault();
    	
    	var href = $(this).attr('href');
    	$.get(href , function(axe,status){
    	
    		$('#IdEdit').val(axe.id_axe);
    		$('#nameEdit').val(axe.axe_name);
    		$('#descriptionEdit').val(axe.axe_description);
    		
    	});
    	
    	$('#editModal').modal();
    });
    $('#modifierpassword').on('click',function(event){
    	event.preventDefault();
    	var href = $(this).attr('href');
    	$.get(href , function(user,status){
    	
    		$('#IdEdituser').val(user.id);
    		$('#matriculeEdit').val(user.matricule);
    		$('#lastNameEdit').val(user.lastName);
    		$('#nameEdit').val(user.name);
    		$('#emailEdit').val(user.email);
    		$('#passwordEdit').val(user.password);
    	
    		
    	});
    	$('#editPasswordModal').modal();
    });
    $('.table #EditEtat').on('click',function(event){
    	event.preventDefault();
    	
    	var href = $(this).attr('href');
    	$.get(href , function(etat,status){
    	
    		$('#idetatEdit').val(etat.id_etat);
    		$('#indicateurEdit').val(etat.indicateur);
    		$('#nameetatEdit').val(etat.nameetat);
    		$('#moisEdit').val(etat.mois);
    		$('#anneeEdit').val(etat.annee);
    		
    	});
    	
    	$('#editModalEtat').modal();
    });
    $('.table .kBtn').on('click',function(event){
    	event.preventDefault();
    	var selectcharge = document.getElementById("nomdchargemesureEdit");
    	var selectvalidateur = document.getElementById("nomdvalidateurEdit");
		selectcharge.innerHTML=null;
		selectvalidateur.innerHTML=null;
    	var href = $(this).attr('href');
    	$.get(href , function(indicateur,status){
    		$('#idindicEdit').val(indicateur.id_indic);
    		$('#nomindicEdit').val(indicateur.nomindic);
    		for(var c=0 ; c<indicateur.tableaucharges.length; c++){
    			var optioncharge = document.createElement("option");
    			optioncharge.text = indicateur.tableaucharges[c].lastName + '.' + indicateur.tableaucharges[c].name ;
    			optioncharge.setAttribute("value",indicateur.tableaucharges[c].lastName + '.' + indicateur.tableaucharges[c].name);
    			selectcharge.appendChild(optioncharge);
    		}
    		
    		for(var e=0 ; e<indicateur.tableauvalidateurs.length; e++){
    			var optionvalidateur = document.createElement("option");
    			optionvalidateur.text = indicateur.tableauvalidateurs[e].lastName + '.' + indicateur.tableauvalidateurs[e].name  ;
    			optionvalidateur.setAttribute("value",indicateur.tableauvalidateurs[e].lastName + '.' + indicateur.tableauvalidateurs[e].name);
    			selectvalidateur.appendChild(optionvalidateur);
    		}
    
    		
    	});
    	
    	$('#editModalAffectation').modal();
    });
      
    $('.table #deletebutton').on('click',function(event){
    	event.preventDefault();
    	var href = $(this).attr('href');
    	$('#deleteModal #delref').attr('href', href);
    	 $('#deleteModal').modal();
    });
    
    $('.table #confirmerButtonalim').on('click',function(event){
    	event.preventDefault();
    	var href = $(this).attr('href');
    	$('#confirmModalAlimenter #Alimenterindic').attr('href', href);
    	 $('#confirmModalAlimenter').modal();
    });
    
    $('.table #deleteUser').on('click',function(event){
    	event.preventDefault();
    	var href = $(this).attr('href');
    	$('#deleteModalUser #delUser').attr('href', href);
    	 $('#deleteModalUser').modal();
    });
    
    $('.table .eBtn').on('click',function(event){
    	event.preventDefault();
    	
    	var href = $(this).attr('href');
    	$.get(href , function(domaine,status){
    	
    		$('#IdEditdomaine').val(domaine.id_domaine);
    		$('#nameEditdomaine').val(domaine.domainename);
    		$('#descriptionEditdomaine').val(domaine.domaine_description);
    		$('#axeidedit').val(domaine.id_axe);
    		
    	});
    	$('#editModalDomaine').modal();
    });
    	
    	
    $('.table #deletebuttondomaine').on('click',function(event){
    	event.preventDefault();
    	var href = $(this).attr('href');
    	$('#deleteModalDomaine #deldom').attr('href', href);
    	 $('#deleteModalDomaine').modal();
    });
    
    $('.table #deleteindic').on('click',function(event){
    	event.preventDefault();
    	var href = $(this).attr('href');
    	$('#deleteModalIndic #delrefindicateur').attr('href', href);
    	 $('#deleteModalIndic').modal();
    });
    $(".sidebar-dropdown > a").click(function() {
    	  $(".sidebar-submenu").slideUp(200);
    	  if (
    	    $(this)
    	      .parent()
    	      .hasClass("active")
    	  ) {
    	    $(".sidebar-dropdown").removeClass("active");
    	    $(this)
    	      .parent()
    	      .removeClass("active");
    	  } else {
    	    $(".sidebar-dropdown").removeClass("active");
    	    $(this)
    	      .next(".sidebar-submenu")
    	      .slideDown(200);
    	    $(this)
    	      .parent()
    	      .addClass("active");
    	  }
    	});

    	$("#close-sidebar").click(function() {
    	  $(".page-wrapper").removeClass("toggled");
    	});
    	$("#show-sidebar").click(function() {
    	  $(".page-wrapper").addClass("toggled");
    	});


 
});

function ajouterLigne()
{       
     if ( typeof this.i == 'undefined' ) this.i = 1;
      
 
    var doc = document.getElementById('treach'); //élément parent
     
   
    
    const  row1 = document.createElement('tr');
       
    const  div1 = document.createElement('td');
          
    const  div2 = document.createElement('td');
               
    const   label1 = document.createElement('label');
    const   label2 = document.createElement('label');      
    const   input = document.createElement('input');
            input.type = "text";
          
            input.className="form-control col-sm-9";	
            input.setAttribute("name","donnees["+i+"].iddonn");
            input.placeholder="Code Donnée";
            input.id = "myinput"; 
            
    const   input2 = document.createElement('input');
            input2.type = "text";
          
            input2.className="form-control col-sm-12";  
            input2.setAttribute("name","donnees["+i+"].nom_donn");
            input2.placeholder="Nom Donées";
            input2.id = "myinput"; 
            

            
   
 
  
             
           
   //Ajouter les éléments
    i += 1;
     
     
   doc.appendChild(row1);
   
   row1.appendChild(div1);
   row1.appendChild(div2);
   div1.appendChild(label1);
   div1.appendChild(input);
   div2.appendChild(label2);
   div2.appendChild(input2);
  
   
   
   
    

 
}