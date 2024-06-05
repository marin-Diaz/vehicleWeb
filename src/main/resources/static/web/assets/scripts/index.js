




const wordContainerEl1 = document.querySelector(".fachero-title1");

const word1 = wordContainerEl1.getAttribute("data-word");

const wordRepeatTimes1 = wordContainerEl1.getAttribute("data-word-repeat");

const textColorsArray1 = wordContainerEl1.getAttribute("data-text-colors").split(",");


for (let i = 0; i < wordRepeatTimes1; i++) {
	const wordEl = document.createElement("span");
	wordEl.className = "word";
	wordEl.style.setProperty("--word-index", i);
	wordEl.style.setProperty("--color", textColorsArray1[i]);
	for (let j = 0; j < word1.length; j++) {
		const charEl = document.createElement("span");
		charEl.className = "char";
		charEl.style.setProperty("--char-index", j);
		charEl.innerHTML = word1[j];
		wordEl.appendChild(charEl);
	}
	wordContainerEl1.appendChild(wordEl);
}


const wordContainerEl2 = document.querySelector(".fachero-title2");

const word2 = wordContainerEl2.getAttribute("data-word");

const wordRepeatTimes2 = wordContainerEl2.getAttribute("data-word-repeat");

const textColorsArray2 = wordContainerEl2.getAttribute("data-text-colors").split(",");


for (let i = 0; i < wordRepeatTimes2; i++) {
	const wordEl = document.createElement("span");
	wordEl.className = "word";
	wordEl.style.setProperty("--word-index", i);
	wordEl.style.setProperty("--color", textColorsArray2[i]);
	for (let j = 0; j < word2.length; j++) {
		const charEl = document.createElement("span");
		charEl.className = "char";
		charEl.style.setProperty("--char-index", j);
		charEl.innerHTML = word2[j];
		wordEl.appendChild(charEl);
	}
	wordContainerEl2.appendChild(wordEl);
}


function verificarPasswords() {
	password1 = document.getElementById('password1');
	password2 = document.getElementById('password2');

	if (password1.value != password2.value) {
		document.getElementById('error').classList.add('mostrar');
		return false;
	} else {
		document.getElementById('error').classList.remove('mostrar');
		document.getElementById('ok').classList.remove('ocultar');
		document.getElementById('crear').disabled = true;
		return true;
	}


}



var t1 = new TimelineMax({ paused: true });

t1.to(".nav-container", 1, {
	left: 0,
	ease: Expo.easeInOut,

});

t1.staggerFrom(
	".menu > div",
	0.8,
	{ y: 100, opacity: 0, ease: Expo.easeOut },
	"0.1",
	"-=0.4"
);

t1.staggerFrom(
	".socials",
	0.8,
	{ y: 100, opacity: 0, ease: Expo.easeOut },
	"0.4",
	"-=0.6"
);

t1.reverse();
$(document).on("click", ".menu-open", function () {
	t1.reversed(!t1.reversed());
});
$(document).on("click", ".menu-close", function () {
	t1.reversed(!t1.reversed());
});


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
  
