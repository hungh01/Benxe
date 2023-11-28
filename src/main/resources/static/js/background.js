const backgrounds = [
    'url("background1.jpg")',
    'url("background2.jpg")',
    'url("background3.jpg")',
    // Thêm các URL hình nền vào đây
  ];
  
  let currentBackgroundIndex = 0;
  const backgroundContainer = document.getElementById('backgroundContainer');
  
  function changeBackground(index) {
    currentBackgroundIndex = index;
    backgroundContainer.style.backgroundImage = backgrounds[index];
  }
  
  // Tự động chuyển đổi hình nền sau một khoảng thời gian
  function autoChangeBackground() {
    setInterval(() => {
      currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
      backgroundContainer.style.backgroundImage = backgrounds[currentBackgroundIndex];
    }, 5000); // Thay đổi mỗi 5 giây
  }
  
  // Thêm các hình nền vào backgroundContainer
  document.addEventListener('DOMContentLoaded', () => {
    backgrounds.forEach(background => {
      const div = document.createElement('div');
      div.style.backgroundImage = background;
      backgroundContainer.appendChild(div);
    });
  
    // Đặt hình nền ban đầu
    backgroundContainer.style.backgroundImage = backgrounds[currentBackgroundIndex];
  
    // Bắt đầu chuyển đổi tự động
    autoChangeBackground();
  });
  