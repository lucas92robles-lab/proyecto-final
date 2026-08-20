

const API_BASE = 'http://localhost:8080/tactica01/api/v1';

// ── Utilidad interna ─────────────────────────────────────────
async function request(url, options = {}) {
    try {
        const res = await fetch(url, {
            headers: { 'Content-Type': 'application/json;charset=utf-8' },
            ...options
        });
        if (!res.ok) {
            const error = await res.text();
            throw new Error(`HTTP ${res.status}: ${error}`);
        }
        // 204 No Content no tiene body
        if (res.status === 204) return null;
        return await res.json();
    } catch (err) {
        console.error(`Error en ${url}:`, err);
        throw err;
    }
}

// ── Medios ───────────────────────────────────────────────────
const MedioAPI = {

    getAll() {
        return request(`${API_BASE}/medios`);
    },

    getById(id) {
        return request(`${API_BASE}/medios/${id}`);
    },

    getByCategoria(categoriaId) {
        return request(`${API_BASE}/medios/categoria/${categoriaId}`);
    },

    create(medio) {
        return request(`${API_BASE}/medios`, {
            method: 'POST',
            body: JSON.stringify(medio)
        });
    },

    update(id, medio) {
        return request(`${API_BASE}/medios/${id}`, {
            method: 'PUT',
            body: JSON.stringify(medio)
        });
    },

    delete(id) {
        return request(`${API_BASE}/medios/${id}`, {
            method: 'DELETE'
        });
    }
};

// ── Catálogos ─────────────────────────────────────────────────
const CatalogoAPI = {

    getPaises() {
        return request(`${API_BASE}/paises`);
    },

    getCategorias() {
        return request(`${API_BASE}/categorias`);
    },

    getFabricantes() {
        return request(`${API_BASE}/fabricantes`);
    }
};