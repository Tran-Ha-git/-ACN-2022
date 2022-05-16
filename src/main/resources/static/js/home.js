var swiper = new Swiper(".suggest-book", {
	slidesPerView: 4,
	grid: {
		rows: 2
	},
	spaceBetween: 30,
	freeMode: true,
	navigation: {
		nextEl: ".suggest-button-next",
		prevEl: ".suggest-button-prev",
	}
});

var swiper = new Swiper(".new-book", {
	slidesPerView: 4,
	grid: {
		rows: 2
	},
	spaceBetween: 30,
	freeMode: true,
	navigation: {
		nextEl: ".new-button-next",
		prevEl: ".new-button-prev",
	}
});

var swiper = new Swiper(".manga-book", {
	slidesPerView: 4,
	grid: {
		rows: 2
	},
	spaceBetween: 30,
	freeMode: true,
	navigation: {
		nextEl: ".manga-button-next",
		prevEl: ".manga-button-prev",
	}
});

const audioTab = document.getElementById("audio-tab");
const onlineTab = document.getElementById("online-tab");
const audioList = document.getElementById("audio-list");
const onlineList = document.getElementById("online-list");

audioTab.onclick = () => {
	audioTab.classList.add("active");
	onlineTab.classList.remove("active");
	
	audioList.classList.add("non-active");
	onlineList.classList.remove("non-active");
}

onlineTab.onclick = () => {
	onlineTab.classList.add("active");
	audioTab.classList.remove("active");

	onlineList.classList.add("non-active");
	audioList.classList.remove("non-active");
}