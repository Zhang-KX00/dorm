document.addEventListener('DOMContentLoaded', function() {
    // 侧边栏折叠
    document.getElementById('sidebarCollapse').addEventListener('click', function() {
        document.getElementById('sidebar').classList.toggle('active');
    });

    // 显示当前活跃菜单项
    const activePage = document.querySelector('.navbar-brand').textContent.toLowerCase();
    const menuItems = document.querySelectorAll('#sidebar ul li a');

    menuItems.forEach(item => {
        if (item.textContent.toLowerCase().includes(activePage)) {
            item.parentElement.classList.add('active');
        }
    });

    // 表单验证
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!this.checkValidity()) {
                e.preventDefault();
                e.stopPropagation();
            }
            this.classList.add('was-validated');
        }, false);
    });
});