function recupererClients() {
	fetch('clients/lister')
			.then(function(resp) {
				return resp.json();
			})
			.then(
					function(listeClients) {
						var clients = listeClients.map(
								function(client) {
									return '<option>' + client.nom + " "
											+ client.prenoms + '</option>';
								}).join('');
						document.getElementById('client').innerHTML = ' <label for="client">client</label><select class="form-control" name="client" id="client">'
								+ clients + '</select>';
					})
}

function recupererChambres() {
	fetch('chambres')
			.then(function(resp) {
				return resp.json();
			})
			.then(
					function(listeChambres) {
						var chambres = listeChambres
								.map(
										function(chambre) {
											return '<div class="row"><input class="form-check-input" type="checkbox" id="'
													+ chambre.uuid
													+ '" ><label class="form-check-label" for="'
													+ chambre.uuid
													+ '">'
													+ chambre.numero
													+ " ("
													+ chambre.surfaceEnM2
													+ " m²) - Hotel "
													+ chambre.hotel.nom
													+ " ("
													+ chambre.hotel.nombreEtoiles
													+ " etoile(s)"
													+ '</label></div>';

										}).join('');
						document.getElementById('cham').innerHTML = '<div class="form-group form-check">'
								+ chambres + '</div>';
					})
}


function ajoutBDD() {
    var dateDebut = document.getElementById("dateDebut");
    var dateFin = document.getElementById("dateFin");
    var ClientId = document.getElementsByName("client");
    var chambres = document.getElementsByName("cham"); 
	var listeChambres=[];
	
	for (i=0 ; i<= chambres.length-1 ; i++)
	{
		
		
			listeChambres.push(chambres[i].id);
		
	}
	
	fetch('reservations',
			{
      
        		method: 'POST',
		headers: {
		"Content-Type": "application/json"
		},
		body: '{"dateDebut": "dateDebut", "dateFin": "dateFin", "ClientId": "ClientId", "chambres": "listeChambres" }'
		}).then(response => response.json());