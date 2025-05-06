
x = 0:0.01:2*pi;
y_clean = sin(x);
noise = 0.4 * randn(size(x));
y_noisy = y_clean + noise;
window_size = 5;
y_smooth = filter(ones(1, window_size)/window_size, 1, y_noisy);
plot(x, y_clean, 'g', 'LineWidth', 2);
hold on;
plot(x, y_noisy, 'r');
plot(x, y_smooth, 'b', 'LineWidth', 2);
legend('Clean sin(x)', 'Noisy', 'Smoothed');
title('Salt and Smooth of sin(x)');
xlabel('x');
ylabel('y');
grid on;
hold off;
