<template>
    <div id="app">

        <app-header/>

        <div class="container">
            <h1 class="pt-3 pb-3">Персонажи Marvel</h1>

            <app-modal/>

            <spinner/>
 
        <div class="row">
        <div class="card mb-3 col-sm-12 col-md-6 col-lg-4">
            <div class="row g-0">
                <div class="col-4">
                    <img src="http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg"
                         alt="Человек-паук"
                         style="max-width: 100%;"
                    >
                </div>
                <div class="col-8">
                    <div class="card-body">
                        <h5 class="card-title">Человек-паук</h5>
                        <button type="button"
                                data-bs-toggle="modal"
                                data-bs-target="#exampleModal"
                                class="btn btn-secondary btn-sm"
                        >
                            Подробнее
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
        </div>

    </div>
</template>

<script>
    import Spinner from "./components/Spinner";
    import AppModal from "./components/AppModal";
    import AppHeader from "./components/AppHeader";

    export default {
        name: 'App',
        components: {
            AppHeader,
            AppModal,
            Spinner,
        },
        data() {
            return {
                loading: false,
                characters: [],
                characterIndex: 0,
            }
        },
        methods: {},
        computed: {},
    }
</script>

<style>

</style>
