import Vue from 'vue';
import CategoryService from '../services/category.service';

export const category = {
    namespaced: true,
    state: {
        parentCategories: [],
        subCategories: [],
        cat: [],
        parent: []
    },
    getters: {
        getParentCategories(state) {
            return state.parentCategories;
        },
        getSubCategories(state) {
            return state.subCategories;
        },
        getCategory(state) {
            return state.cat;
        },
        getParent(state) {
            return state.parent;
        }

    },
    mutations: {
        initParents(state, parentCategories) {
            state.parentCategories = parentCategories;
        },
        addParent(state, parentCategory) {
            state.parentCategories.push(parentCategory);
        },
        updateParent(state, parentCategory) {
            let index = state.parentCategories.findIndex(c => c.id == parentCategory.id);
            //Güncelleme işlemini DOM'da gerçekleştiriyoruz ki child'da yaptığım değişiklik doğrudan ebeveyn'e yansısın
            //state.parentCategories.splice(index, 1, parentCategory)
            Vue.set(state.parentCategories, index, parentCategory);

        },
        deleteParent(state, parentCategoryId) {
            state.parentCategories.forEach(parent => {
                if (parent.id == parentCategoryId) {
                    state.parentCategories.splice(parent, 1);
                }
            });
        },
        initSubs(state, subCategories) {
            state.subCategories = subCategories;
        },
        initCategory(state, cat) {
            state.cat = cat;
        },
        initParentCategory(state, parent) {
            state.parent = parent;
        },
    },
    actions: {
        getParents(state) {
            CategoryService.getParentCategories().then(
                parentCategories => {
                    state.commit('initParents', parentCategories);
                }
            );
        },
        addParentCategory(state, parentCategory) {
            //db add işlemi
            state.commit('addParent', parentCategory);
        },
        updateParentCategory(state, parentCategory) {
            //Db update işlemi
            state.commit('updateParent', parentCategory);
        },
        deleteParentCategory(state, parentCategoryId) {
            state.commit('deleteParent', parentCategoryId);
        },
        getSubs({ commit }, id) {
            CategoryService.getSubCategory(id).then(
                subCategories => {
                    commit('initSubs', subCategories);
                }
            );
        },
        getCategory({ commit }, id) {
            CategoryService.getCategory(id).then(cat => {
                commit('initCategory', cat);
            });
        },
        getParentCategory({ commit }, id) {
            CategoryService.getParentCategory(id).then(parent => {
                commit('initParentCategory', parent);
            });
        }

    },

};