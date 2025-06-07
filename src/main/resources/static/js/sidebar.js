document.addEventListener('DOMContentLoaded', function() {
    // 侧边栏折叠功能
    const sidebarCollapse = document.getElementById('sidebarCollapse');
    if (sidebarCollapse) {
        sidebarCollapse.addEventListener('click', function() {
            document.getElementById('sidebar').classList.toggle('active');
        });
    }

    // 激活当前菜单项
    const currentPage = window.location.pathname;
    document.querySelectorAll('#sidebar a').forEach(link => {
        if (link.getAttribute('href') === currentPage) {
            link.parentElement.classList.add('active');
            // 展开父级菜单
            const parentMenu = link.closest('.collapse');
            if (parentMenu) {
                parentMenu.classList.add('show');
            }
        }
    });
});