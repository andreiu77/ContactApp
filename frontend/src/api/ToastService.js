import { toast } from "react-toastify";

const toastConfig = {
    position: 'bottom-right',
    autoClose: 4000,
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: false,
    draggable: false,
    theme: "light"
};

export function toastInfo(message){
    toast.info(message, toastConfig);
}

export function toastSuccess(message){
    toast.success(message, toastConfig);
}

export function toastWarn(message){
    toast.warn(message, toastConfig);
}

export function toastError(message){
    toast.error(message, toastConfig);
}