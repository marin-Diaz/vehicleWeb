function hideHeader () {


	var doc = document.documentElement;
	var w = window;





	var curScroll = prevScroll = w.scrollY || doc.scroll(top);
	var curDirection = prevDirection = 0;

	var header = document.getElementById('menu-open');

	function checkScroll(){
		curScroll = window.scrollY || doc.scroll(top)
		if(curScroll > prevScroll){
			curDirection = 2;
		}
		else{
			curDirection = 1;
		}

		if (curDirection !== prevDirection) {
			toggleHeader();
		}

		prevDirection = curDirection;
		prevScroll = curScroll;

	}

	function toggleHeader(){

		if (curDirection === 2) {
			header.classList.add('hide');
		}

		else if (curDirection === 1){
			header.classList.remove('hide');
		}
	}

	w.addEventListener('scroll', checkScroll)

}


hideHeader()


const contenedor = document.querySelector('.contenedor_general')
const colores =['#CB51EE','#0073BC','#38B75E','#DE365C']


const figuras = () =>{
	for(let i= 0; i <= 35; i++){
		let figura = document.createElement('span')
		let select = Math.round(colores.length * Math.random())



		figura.style.top = innerHeight*Math.random() + 'px'
		figura.style.left = innerWidth*Math.random() + 'px'
		figura.style.background = colores[select >= colores.length ?  select -1: select]

		contenedor.appendChild(figura)

		setInterval(() =>{
			figura.style.top = innerHeight*Math.random() + 'px'
			figura.style.left = innerWidth*Math.random() + 'px'
		},2000)
	}
  }

  figuras()