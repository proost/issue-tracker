var main = {
    isPasswordEquals: false,
    password: "",
    init: function() {
        let _self = this;

        _self.listenPasswordChange();
        _self.validateSubmit();
        _self.cancelRegistration();
    },
    listenPasswordChange: function() {
        let _self = this;

        let passwordInput = document.getElementById("password");
        passwordInput.addEventListener("change", () => {
            _self.password = passwordInput.value;
        });

        let passwordConfirmInput = document.getElementById("password-confirm");
        passwordConfirmInput.addEventListener("change", () => {
            let passwordConfirmMsg = document.getElementById("password-confirm-msg");
            if (_self.password === passwordConfirmInput.value) {
                passwordConfirmMsg.textContent = "비밀번호가 일치합니다.";
                _self.isPasswordEquals = true;
            } else {
                passwordConfirmMsg.textContent = "비밀번호가 일치하지 않습니다.";
                _self.isPasswordEquals = false;
            }
        });
    },
    validateSubmit: function() {
        let _self = this;

        let registrationForm = document.getElementById("registration-form");
        registrationForm.addEventListener("submit", (e) => {
            if (!_self.isPasswordEquals) {
                e.preventDefault();
                alert("비밀번호가 반드시 일치해야 합니다.");
            }
        });
    },
    cancelRegistration: function() {
        let _self = this;

        let cancelButton = document.getElementById("cancel-button");
        cancelButton.addEventListener("click", () => {
            window.location.href = "/login";
        });
    }
}

main.init();