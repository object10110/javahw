const Slider = {
    selectors: {
        s_slider: ".slide-holder",
        s_img: ".gallery-img",
        s_sliderNext: ".slider-next",
        s_sliderPrev: ".slider-prev",
        s_sliderAutoPlay: "#slider-auto-play",
        s_sliderAutoPlayInterval: ".slider-interval",
    },
    state: {
        current: 0,
        count: 0,
        width: 0,
        timer: null,
        interval: {
            current: 0,
            default: 3000,
            min: 500
        },
    },
    init() {
        this.setUpListeners();

        const sel = this.selectors;
        const slides = document.querySelectorAll(`${sel.s_slider} ${sel.s_img}`);
        this.state.count = slides.length;

        this.getSlideWidth();
        this.onChangeInterval(this.state.interval.default, true);
    },
    setUpListeners() {
        document.querySelector(this.selectors.s_sliderNext).addEventListener('click', () => this.onNext());
        document.querySelector(this.selectors.s_sliderPrev).addEventListener('click', () => this.onPrev());
        document.querySelector(this.selectors.s_sliderAutoPlay).addEventListener('click', () => {
            if (this.state.timer) {
                this.onStopAutoPlay();
            } else {
                this.onStartAutoPlay();
            }
        });
        document.querySelector(this.selectors.s_sliderAutoPlayInterval).addEventListener('change', (e) => {
            const val = e.target.value;
            this.onChangeInterval(val);
        });
        window.addEventListener('resize', () => {
            this.getSlideWidth();
            this.onUpdate();
        });
    },

    getSlideWidth() {
        const slide = document.querySelector(this.selectors.s_img);
        if (slide) {
            this.state.width = slide.clientWidth;
        }
    },
    onNext() {
        const state = this.state;
        state.current++;
        if (state.current >= state.count) {
            state.current = 0;
        }
        this.onUpdate();
    },
    onPrev() {
        const state = this.state;
        state.current--;
        if (state.current < 0) {
            state.current = state.count - 1;
        }
        this.onUpdate();
    },
    onStartAutoPlay() {
        const state = this.state;
        if (state.timer) {
            this.onStopAutoPlay();
        }
        state.timer = setInterval(() => this.onNext(), state.interval.current);
    },
    onStopAutoPlay() {
        clearInterval(this.state.timer);
        this.state.timer = null;
    },
    onUpdate() {
        document.querySelector(this.selectors.s_slider).style.transform = `translateX(-${this.state.width * this.state.current}px)`;
    },
    onChangeInterval(val, changeElem) {
        const state = this.state;
        if (val && isFinite(+val) && +val >= state.interval.min) {
            state.interval.current = +val;
            if(changeElem){
                document.querySelector(this.selectors.s_sliderAutoPlayInterval).value = +val;
            }
            if (state.timer) {
                this.onStartAutoPlay();
            }
        } else {
            alert("invalid interval, minimum " + this.state.interval.min);
        }
    },
};
Slider.init();