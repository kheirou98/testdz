function ajouterLigneInsert()
{       
     if ( typeof this.i == 'undefined' ) this.i = 1;
      
    var href=$('#addData').attr('href');
    
    var doc = document.getElementById('treach'); //élément parent
     
   
    
    const  row1 = document.createElement('tr');
       
    const  div1 = document.createElement('td');
          
    const  div2 = document.createElement('td');
    
    const  div3 = document.createElement('td');
    
    const  div4 = document.createElement('td');
            
    const   label1 = document.createElement('label');
            label1.className="col-form-label";
    		label1.htmlFor="recipient-name";
    		label1.innerText="Donnée: ";
    		label1.style.display="inline-block";
    		label1.style.marginRight="5px";
    		
    const   label2 = document.createElement('label');
    		label2.className="col-form-label";
    		label2.htmlFor="recipient-name";
    		label2.innerText="Valeur:";
    		label2.style.display="inline-block";
    		label2.style.marginRight="5px";	
   
    const selectdonnees=document.createElement('select');
			selectdonnees.className="form-control";
			selectdonnees.style.display="inline-block";
			selectdonnees.style.width="auto";
			selectdonnees.style.marginRight="5px"; 
			selectdonnees.setAttribute("name","valeursindic["+i+"].iddonn");
		
		$.get(href, function (indicateur, status){
				selectdonnees.options.length=0;
				(indicateur.donnees).forEach(donnee => {
						var opt = document.createElement('option');
					    opt.value = donnee.nom_donn;
					    opt.innerHTML = donnee.nom_donn;
					    selectdonnees.appendChild(opt);
					
				});
			});
			
            
    const   input2 = document.createElement('input');
            input2.type = "text";
          
            input2.className="form-control";  
            input2.setAttribute("name","valeursindic["+i+"].contenu");
            input2.id = "myinput"; 
    		input2.style.width="auto";
    		input2.style.display="inline-block";
    		input2.type="text";
            
    const   input3 = document.createElement('input');
            input3.type = "text";
          
            input3.className="form-control ";  
            input3.setAttribute("name","valeursindic["+i+"].mois");
            input3.setAttribute("value",$('#mois').text());
            input3.id = "myinput"; 
            input3.style.display="none";
  
    const   input4 = document.createElement('input');
            input4.type = "text";
          
            input4.className="form-control ";  
            input4.setAttribute("name","valeursindic["+i+"].annee");
            input4.setAttribute("value",$('#annee').text());
            input4.id = "myinput"; 
            input4.style.display="none";


            
   
 
  
             
           
   //Ajouter les éléments
    i += 1;
     
     
   doc.appendChild(row1);
   
   row1.appendChild(div1);
   row1.appendChild(div2);
   row1.appendChild(div3);
   row1.appendChild(div4);
   div1.appendChild(label1);
   div1.appendChild(selectdonnees);
   div2.appendChild(label2);
   div2.appendChild(input2);
   div3.appendChild(input3);
   div4.appendChild(input4);
  
   
   
   
    

 
}