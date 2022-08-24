class App {
    static DOMAIN = location.origin;

    // static BASE_URL_AUTH = this.DOMAIN + "/api/auth";

    static BASE_URL_MOVIE = this.DOMAIN + "/api/movies";
    static BASE_URL_CATEGORY = this.DOMAIN + "/api/categories";

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }

    static showConfirmDelete() {
        return Swal.fire({
            title: 'Are you sure to delete this property?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        })
    }

    static showError401() {
        Swal.fire({
            icon: 'error',
            title: 'Access Denied',
            text: 'Invalid credentials!',
        })
    }

    static showError403() {
        Swal.fire({
            icon: 'error',
            title: 'Access Denied',
            text: 'You are not authorized to perform this function!',
        })
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    static formatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "").replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }

    static removeFormatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "")
    }

    static IziToast = class  {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                timeout: 2000,
                message: m,
            });
        }

        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2000,
                message: m,
            });
        }
    }

    static drawRowMovie(id, title, premiereDate, showDuration, categoryDetail, director, actor, language, description) {

        let cate = ``;

        $.each(categoryDetail, (key, value) => {
            cate += `<span class="badge badge-warning">${value}</span>`;
        })

        let str = `
            <tr id="tr_${id}" >
                <td>
                    <b>${id}</b>
                </td>
                <td>
                    ${title}
                </td>
                <td>
                    ${premiereDate}
                </td>
                <td>
                    ${showDuration}
                </td>
                <td id="tdCategory_${id}">
                    ${cate}
<!--                    <span class="badge badge-secondary">Low</span>-->
<!--                    <span class="badge badge-success">Open</span>-->
                </td>
                <td>
                    ${director}
                </td>
                <td>
                     ${actor}
                </td>
                <td>
                    ${language}
                </td>
                <td>
                    ${description}
                </td>
        `;

        return str;
    }

    static drawCheckboxCategory(id, category){
        let str = `
            <div class="col-lg-3">
                <div class="custom-control custom-checkbox custom-checkbox-info mb-3">
                    <input type="checkbox" class="custom-control-input category" id="category_${id}" name="${category}">
                    <label class="custom-control-label" for="category_${id}">${category}</label>
                </div>    
            </div>
                
        `;
        return str;
    }

}



class Movie {
    constructor(id, title, premiereDate, showDuration, categories, director, actor, language, description) {
        this.id = id;
        this.title = title;
        this.premiereDate = premiereDate;
        this.showDuration = showDuration;
        this.categories = categories;
        this.director = director;
        this.actor = actor;
        this.language = language;
        this.description = description;
    }
}
class Category {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}









