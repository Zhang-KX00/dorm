// 通用确认对话框
document.addEventListener('DOMContentLoaded', function() {
    // 删除确认
    const deleteButtons = document.querySelectorAll('.delete-btn');
    deleteButtons.forEach(btn => {
        btn.addEventListener('click', function(e) {
            if (!confirm('确定要删除此项吗？')) {
                e.preventDefault();
            }
        });
    });

    // 表单验证
    const forms = document.querySelectorAll('.needs-validation');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!form.checkValidity()) {
                e.preventDefault();
                e.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });

    // 密码匹配验证
    const passwordFields = document.querySelectorAll('input[name="password"], input[name="confirmPassword"]');
    if (passwordFields.length === 2) {
        passwordFields.forEach(field => {
            field.addEventListener('input', function() {
                const password = document.querySelector('input[name="password"]').value;
                const confirmPassword = document.querySelector('input[name="confirmPassword"]').value;
                const feedback = document.querySelector('.password-feedback');

                if (password && confirmPassword && password !== confirmPassword) {
                    feedback.style.display = 'block';
                } else {
                    feedback.style.display = 'none';
                }
            });
        });
    }
});