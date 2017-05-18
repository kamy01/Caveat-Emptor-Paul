	function createBreadcrumb(data) {
				
				$('#breadcrumb').empty();
				var breadcrumb = getBreadcrumb(data);
				
				for (var i = breadcrumb.length -1 ; i >= 0; --i) {
					var li = document.createElement('li');
					li.setAttribute('class', 'breadcrumb-item');

					var id = breadcrumb[i]['nodeId'];
					li.setAttribute('id', id);

					li.setAttribute('onclick', 'selectNode(id)');

					var a = document.createElement('a');
					a.setAttribute('href', '#');
					a.setAttribute('id', 'id' + i);
					li.appendChild(a); +

					document.getElementById('breadcrumb').appendChild(li);
					document.getElementById('id' + i).innerHTML = breadcrumb[i]['text'];
				}
				

				var selectedId = breadcrumb[0]['nodeId'];
				var list = document.getElementById(selectedId);
				list.setAttribute('class', 'breadcrumb-item active');
				list.removeChild(list.childNodes[0]);
				
				document.getElementById(data["nodeId"]).innerHTML = breadcrumb[0]['text'];
			}
		