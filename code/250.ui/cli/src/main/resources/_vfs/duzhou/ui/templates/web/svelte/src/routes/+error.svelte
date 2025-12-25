<script lang="ts">
	import { page } from '$app/state';
	import { onPageMount } from '$lib/common/page.js';
    import { onMount } from 'svelte';

    onMount(onPageMount);
</script>

<h1>{page.status}: {page.error.message}</h1>
